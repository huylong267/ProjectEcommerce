package application.controller.admin;


import application.data.model.Product;
import application.data.service.page.CategoryServiceImp;
import application.data.service.page.ProductServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminProductController {
    @Autowired
    private ProductServiceImp productServiceImp;

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @GetMapping("/admin/product/add")
    public String createProduct(Model model){
        model.addAttribute("categories",categoryServiceImp.findAllCate());
        model.addAttribute("product", new Product());
        return "admin/product_form";
    }

    @GetMapping("/admin/product/getListproduct")
    public  String getListproduct (Model model){
        model.addAttribute("listproduct",productServiceImp.findAllProduct());
        model.addAttribute("categories",categoryServiceImp.findAllCate());
        return "admin/product_list";
    }

    @GetMapping("/admin/product/getProduct/{id}")
    public String getProduct(@PathVariable int id, Model model){

        model.addAttribute("product",productServiceImp.findOneProduct(id));
        return "admin/product_form";
    }
//
//    String[] image ={
//            "/link/1544691764-img1.jpg","/link/1544691809-img4.jpg","/link/1544691831-img5.jpg","/link/1544691894-img6.jpg" ,
//            "/link/1544691932-img9.jpg","/link/1544691952-img10.jpg"
//    };
//    @GetMapping("/admin/product/fakeproduct")
//    public String fakeProduct(){
//        for(int i =0 ;i<=5;i++){
//            Product product = new Product();
//            product.setProduct_id(i+1);
//            product.setPrice(1000000);
//            product.setCreated_date(new Date());
//            product.setName("Áo nam "+ i);
//            product.setDesc("Chi tiết áo nam "+i);
//            product.setCategory(categoryServiceImp.findOneCate(3));
//            for (int j=i; j < image.length;j++){
//                product.setImage(image[i]);
//            }
//            productServiceImp.addNewProduct(product);
//
//        }
//        return "admin/product_list";
//    }
}
