package application.controller.page;

import application.data.model.Category;
import application.data.model.Order;
import application.data.repository.web.iCategoryRepository;

import application.data.service.auth.UserService;
import application.data.service.auth.UserServiceImp;
import application.data.service.page.CategoryServiceImp;
import application.data.service.page.NewsServiceImp;
import application.data.service.page.OrderService;
import application.data.service.page.ProductServiceImp;
import application.model.NewsVm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;

import static application.constant.StatusOrderConstant.unpaid;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @Autowired
    private NewsServiceImp newsServiceImp;
    @Autowired
    private ProductServiceImp productServiceImp;


    @GetMapping("/")
    public String home(Model model ,HttpServletResponse httpServletResponse){
        List<Category> categorryList = categoryServiceImp.findAllCate();
        model.addAttribute("categories", categorryList);
        model.addAttribute("productShirtHome",productServiceImp.listProductHome(3));
        model.addAttribute("producttrousersHome",productServiceImp.listProductHome(15));
        model.addAttribute("productOtherHome",productServiceImp.listProductHome(9));
        return "home";
    }
    @GetMapping(path="/list-news")
    public String index(Model model){
        NewsVm newsVm = new NewsVm();
        newsVm.setListNewNews(newsServiceImp.getNewnews());
        newsVm.setListNews(newsServiceImp.findAllNews());
        model.addAttribute("categories", categoryServiceImp.findAllCate());
        model.addAttribute("list",newsVm);
        return "blog";
    }
    @GetMapping(path="/user/{id}")
    public String user(){
        return "user";
    }

    @GetMapping(path = "/lienhe")
    public String gioiThieu (Model model){
            model.addAttribute("categories", categoryServiceImp.findAllCate());
        return "lienhe";
    }
}
