package application.controller.api;

import application.data.model.Product;
import application.data.model.ProductDetail;
import application.data.service.page.*;
import application.model.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(path = "/api/product")
public class ProductApiController {
    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @Autowired
    private ProductServiceImp  productServiceImp;


    @PostMapping(path = "/createproduct")
    public BaseApiResult createProduct(@RequestBody ProductDataModel product) {
        DataApiResult result = new DataApiResult();
        try {
            if(!"".equals(product.getName()) && !"".equals(product.getDesc()) && !"".equals(product.getImage())) {
                ModelMapper modelMapper = new ModelMapper();
                Product productEntity = modelMapper.map(product, Product.class);
                productEntity.setCategory(categoryServiceImp.findOneCate(product.getCategory_id()));
                productServiceImp.addNewProduct(productEntity);
                result.setSuccess(true);
                result.setMessage("Save product successfully: " + productEntity.getProduct_id());
                result.setData(productEntity);
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

    @PostMapping("/update-product/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId,
                                       @RequestBody ProductEditModel product) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(!"".equals(product.getName()) && !"".equals(product.getDesc())
                    && !"".equals(product.getImage())) {
                // check existed product
                Product existProduct = productServiceImp.findOneProduct(productId);
                if(existProduct == null) {
                    result.setSuccess(false);
                    result.setMessage("Invalid model");
                } else {
                    existProduct.setImage(product.getImage());
                    existProduct.setName(product.getName());
                    existProduct.setUpdated_date(new Date());
                    existProduct.setDesc(product.getDesc());
                    existProduct.setCategory(categoryServiceImp.findOneCate(product.getCategory_id()));
                    productServiceImp.updateproduct(existProduct);
                    result.setSuccess(true);
                    result.setMessage("Update product successfully");
                }
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

    @GetMapping("/getproduct/{productId}")
    public BaseApiResult detailProduct(@PathVariable int productId) {
        DataApiResult result = new DataApiResult();
        try{
            Product existProduct = productServiceImp.findOneProduct(productId);
            if(existProduct == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                result.setSuccess(true);
                ModelMapper modelMapper = new ModelMapper();
                ProductGetModel productGetModel =
                        modelMapper.map(existProduct, ProductGetModel.class);
                result.setData(productGetModel);
                result.setMessage("Successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/delete-product")
    public BaseApiResult deleteProduct(@RequestBody ProductDeleteModel product) {
        BaseApiResult result = new BaseApiResult();
        try {
                productServiceImp.deleteProduct(product.getProduct_id());
                result.setSuccess(true);
                result.setMessage("Delete product successfully");

        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @GetMapping("/list-product")
    public BaseApiResult getListProductIMG(@RequestParam("term") String name){
        DataApiResult result = new DataApiResult();
        try {
            List<Product> productExist = productServiceImp.findbyName(name);
            ProductImgModel productImgModel = new ProductImgModel();
            List<ProductImgModel>  imgModels= new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();
            for(Product product: productExist){
                 productImgModel = modelMapper.map(product,ProductImgModel.class);
                imgModels.add(productImgModel);
            }

            result.setData(imgModels);
            result.setMessage("Successfully");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMessage("Fail");
            result.setSuccess(false);
        }
        return result;
    }
}
