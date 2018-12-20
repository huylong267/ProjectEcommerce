package application.controller.api;

import application.data.model.ProductDetail;
import application.data.service.page.ColorServiceImp;
import application.data.service.page.ProductDetailServiceImp;
import application.data.service.page.ProductServiceImp;
import application.data.service.page.SizeServiceImp;
import application.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/productdetail")
public class    ProductDetailApiController {
    @Autowired
    private ProductServiceImp productServiceImp;
    @Autowired
    private ProductDetailServiceImp productDetailServiceImp;
    @Autowired
    private ColorServiceImp colorServiceImp;
    @Autowired
    private SizeServiceImp sizeServiceImp;

    @PostMapping(path = "/create-product-detail")
    public BaseApiResult createProduct(@RequestBody ProductDetailCreateModel product) {
        DataApiResult result = new DataApiResult();
        try {
            if (!"".equals(product.getQuantity()) && !"".equals(product.getColor_id()) && !"".equals(product.getSize_id()) && !"".equals(product.getImage())) {

                ProductDetail productDetail = new ProductDetail();
                productDetail.setProduct(productServiceImp.findOneProduct(product.getProduct_id()));
                productDetail.setSize(sizeServiceImp.findOneSize(product.getSize_id()));
                productDetail.setColor(colorServiceImp.findOneColor(product.getColor_id()));
                productDetail.setImage(product.getImage());
                List<ProductDetail> listProduct = productDetailServiceImp.findByProductId(product.getProduct_id());
                if (listProduct.size()==0) {
                    productDetail.setQuantity(product.getQuantity());
                    productDetailServiceImp.createDetail(productDetail);
                }else {
                    for (ProductDetail detail : listProduct) {
                        if (productDetail.getSize().getSize_id() == detail.getSize().getSize_id() &&
                                productDetail.getColor().getColor_id() == detail.getColor().getColor_id()) {
                            int quantity = product.getQuantity() + detail.getQuantity();
                            productDetail.setQuantity(quantity);
                            ProductDetail detailExist =productDetailServiceImp.getByDetail(product.getProduct_id(),product.getSize_id(),product.getColor_id());
                            productDetailServiceImp.delete(detailExist.getProductdetail_id());
                            productDetailServiceImp.createDetail(productDetail);
                        }
                        else {
                            productDetail.setQuantity(product.getQuantity());
                            productDetailServiceImp.createDetail(productDetail);
                        }
                    }

                }
                result.setSuccess(true);
                result.setMessage("Save product successfully: " + productDetail.getProductdetail_id());
                result.setData(productDetail.getProduct());
            } else {
                result.setSuccess(false);
                result.setMessage("Invalid model");
            }
        } catch (Exception e) {
              result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/update-detail/{productDetailId}")
    public BaseApiResult updateDetail (@PathVariable int productDetailId ,
                                       @RequestBody ProductDetailEditModel editModel){

        DataApiResult result = new DataApiResult();
        try {
            if(!"".equals(editModel.getQuantity())){
                ProductDetail productDetailExist = productDetailServiceImp.findOne(productDetailId);
                if(productDetailExist == null){
                    result.setMessage("productDetail null");
                    result.setSuccess(false);
                }else {
                    productDetailExist.setImage(editModel.getImage());
                    productDetailExist.setQuantity(editModel.getQuantity());
                    productDetailExist.setColor(colorServiceImp.findOneColor(editModel.getColor_id()));
                    productDetailExist.setSize(sizeServiceImp.findOneSize(editModel.getSize_id()));
                    productDetailServiceImp.updateDetail(productDetailExist);
                    result.setSuccess(true);
                    result.setData(productDetailExist);
                    result.setMessage("Successfully");
                }
            }else {
                result.setMessage("Fail");
                result.setSuccess(false);
            }
        } catch (Exception e) {
            result.setMessage("FAIL CATCH");
            result.setSuccess(false);
        }
        return result;
    }
    @GetMapping("/getdetailproduct/{id}")
    public BaseApiResult getDetailProduct(@PathVariable int id){
        DataApiResult result = new DataApiResult();
        ProductDetail productDetail = productDetailServiceImp.findOne(id);
        try {
            if(productDetail == null){
                result.setSuccess(false);
                result.setMessage("Product Detail is null");
            }else {
                result.setMessage("Successfully");
                result.setSuccess(true);
                ModelMapper mapper = new ModelMapper();
                ProductDetailModel productDetailModel = mapper.map(productDetail,ProductDetailModel.class);
                result.setData(productDetailModel);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Product Detail is catch");
        }
        return  result;
    }

    @PostMapping("/delete-productdetail")
    public DataApiResult deleteProductDetail(@RequestBody ProductDetaiDeleteModel deleteModel){
        DataApiResult result = new DataApiResult();
        try {
            productDetailServiceImp.delete(deleteModel.getProductdetail_id());
            result.setSuccess(true);
            result.setMessage("Delete model successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Delete model fail");
        }
        return result;
    }

    @GetMapping("/findproductDetailbyColor/{productId}")
    public BaseApiResult findproductDetailbyColor (@PathVariable int productId,@RequestParam("optradio")int optradio){
        DataApiResult result = new DataApiResult();
        try {
            ProductDetail productDetail = productDetailServiceImp.findproductDetailbyColor(optradio,productId);
            result.setData(productDetail);
            result.setSuccess(true);
            result.setMessage("SUCCESSFULLY");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("FAIL");
        }
        return result;
    }

}

