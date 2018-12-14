package application.controller.page;

import application.constant.Constant;
import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.service.page.CategoryServiceImp;
import application.data.service.page.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CollectionsController {
    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @Autowired
    private ProductServiceImp productServiceImp;
    @GetMapping("/collection")
    public String collection(Model model, @RequestParam(value="pageNumber", required=false)
            Integer pageNumber){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            model.addAttribute("username",((UserDetails) principal).getUsername());
        }
        int pageSize = Constant.DEFAULT_PAGE_SIZE;

        if(pageNumber == null) {
            pageNumber = 1;
        }
        PaginableItemList<Product> paginableItemList = productServiceImp.
                getListProducts(Constant.DEFAULT_PAGE_SIZE,
                pageNumber - 1);
        int totalPages = 0;
        if(paginableItemList.getTotalProducts() % pageSize == 0) {
            totalPages = (int)(paginableItemList.getTotalProducts() / pageSize);
        } else {
            totalPages = (int)(paginableItemList.getTotalProducts() / pageSize) + 1;
        }
        List<Product> listProducts = paginableItemList.getListData();

        model.addAttribute("products",listProducts);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("categories", categoryServiceImp.findAllCate());
        return "collections";
    }
    @GetMapping("/productbycategory")
    public String showproductbyid(Model model, @RequestParam("categoryId") int categoryId,@RequestParam(value="pageNumber", required=false )Integer pageNumber) {
       // List<Product> productList = productServiceImp.listproductBycategory(categoryId);
        int pageSize = Constant.DEFAULT_PAGE_SIZE;

        if(pageNumber == null) {
            pageNumber = 1;
        }
        PaginableItemList<Product> paginableItemList = productServiceImp.
                getListProductPaging(Constant.DEFAULT_PAGE_SIZE,
                        pageNumber - 1,categoryId);
        int totalPages = 0;
        if(paginableItemList.getTotalProducts() % pageSize == 0) {
            totalPages = (int)(paginableItemList.getTotalProducts() / pageSize);
        } else {
            totalPages = (int)(paginableItemList.getTotalProducts() / pageSize) + 1;
        }
        System.out.println("TOTAL----------"+totalPages);
        List<Product> listProducts = paginableItemList.getListData();
        model.addAttribute("products", listProducts);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("categories", categoryServiceImp.findAllCate());
        return "collections";

    }
}
