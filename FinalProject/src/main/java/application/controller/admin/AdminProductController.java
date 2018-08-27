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
}
