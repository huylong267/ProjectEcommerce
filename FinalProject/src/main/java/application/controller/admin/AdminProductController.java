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

    String[] image ={
            "/link/1544762404-anh5.jpg","/link/1544762433-anh7.jpg","/link/1544762469-megamenu_1_image_1.jpg","/link/1544762502-anh8.jpg" ,
            "/link/1544762520-anh12.jpg","/link/1544762534-anh11.jpg","/link/1544762547-anh3.jpg"
    };
    @GetMapping("/admin/product/fakeproduct")
    public String fakeProduct(){
        for(int i =0 ;i<=5;i++){
            Product product = new Product();
            product.setProduct_id(i+1);
            product.setPrice(1000000);
            product.setCreated_date(new Date());
            product.setName("Phụ kiện nam "+ i);
            product.setDesc("Chi tiết Phụ kiện nam "+i);
            product.setCategory(categoryServiceImp.findOneCate(9));
            for (int j=i; j < image.length;j++){
                product.setImage(image[i]);
            }
            productServiceImp.addNewProduct(product);

        }
        return "admin/product_list";
    }
}
