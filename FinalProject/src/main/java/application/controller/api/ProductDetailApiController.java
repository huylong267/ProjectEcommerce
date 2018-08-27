package application.controller.api;

import application.data.model.Product;
import application.data.model.ProductDetail;
import application.data.service.page.ColorServiceImp;
import application.data.service.page.ProductDetailServiceImp;
import application.data.service.page.ProductServiceImp;
import application.data.service.page.SizeServiceImp;
import application.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/productdetail")
public class ProductDetailApiController {
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
            if (!"".equals(product.getQuantity()) && !"".equals(product.getColor_id()) && !"".equals(product.getSize_id())) {

                ProductDetail productDetail = new ProductDetail();
                productDetail.setProduct(productServiceImp.findOneProduct(product.getProduct_id()));
                productDetail.setSize(sizeServiceImp.findOneSize(product.getSize_id()));
                productDetail.setColor(colorServiceImp.findOneColor(product.getColor_id()));

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


}

