package application.controller.admin;

import application.Application;
import application.data.model.Product;
import application.data.model.ProductDetail;
import application.data.service.page.*;
import application.model.ProductDetailModel;
import application.model.ProductNameModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminProductDetailController {
    private static final Logger logger = LogManager.getLogger(AdminProductDetailController.class);

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @Autowired
    private SizeServiceImp sizeServiceImp;

    @Autowired
    private ColorServiceImp colorServiceImp;

    @Autowired
    private ProductDetailServiceImp productDetailServiceImp;
    @GetMapping("/admin/productdetail/add")
    public String addDetail(Model model){
        model.addAttribute("categories",categoryServiceImp.findAllCate());
        model.addAttribute("colors",colorServiceImp.findAllColor());
        model.addAttribute("sizes",sizeServiceImp.findAllSize());
        return "admin/productdetail_form";
    }

    @GetMapping("/admin/productdetail/getListproductDetail")
    public  String getListproductDetail (Model model){
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDetail> products = productDetailServiceImp.findAllDetail();
        List<ProductDetailModel> productDetailModels = new ArrayList<>();
        for(ProductDetail product : products){
            ProductDetailModel productDetailModel = modelMapper.map(product,ProductDetailModel.class);
            productDetailModels.add(productDetailModel);
            logger.info("Product{}",productDetailModel);
        }
        model.addAttribute("listproductdetail",productDetailModels);
        return "admin/productdetail_list";
    }
}
