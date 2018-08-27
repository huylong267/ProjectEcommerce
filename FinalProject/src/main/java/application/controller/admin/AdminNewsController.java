package application.controller.admin;

import application.data.model.News;
import application.data.service.page.NewsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminNewsController {
    @Autowired
    private NewsServiceImp newsServiceImp;
    @GetMapping("admin/news")
    public String news(Model model) {
        model.addAttribute("listnews",newsServiceImp.findAllNews());
        return"admin/news_list";
    }
    @GetMapping("admin/news/add")
    public String newsAdd(Model model) {
        model.addAttribute("news",new News());
        return "admin/news_form";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }
    @PostMapping("/admin/news/save")
    public String newsSave(@Valid News news, BindingResult result , RedirectAttributes redirect){


        if(result.hasErrors()){
            System.out.println("Loi");
            return "admin/news_form";
        }else if(news.getCreated_date()==null){

            news.setCreated_date(new Date());
            news.setUpdated_date(new Date());
            newsServiceImp.saveNews(news);
            redirect.addFlashAttribute("success","Lưu thành công tin");
            return "redirect:/admin/news/";
        }else if (news.getCreated_date() !=null){
            news.setUpdated_date(new Date());
            newsServiceImp.saveNews(news);
            redirect.addFlashAttribute("success","Lưu thành công tin");
            return "redirect:/admin/news/";
        }

        return "redirect:/admin/news/";
    }
    @GetMapping("/admin/news/{id}/edit")
    public String editNews(@PathVariable("id") Integer id,Model model){
        News news = newsServiceImp.findOneNews(id);
        model.addAttribute("news", news);
        return "admin/news_form";
    }
    @GetMapping("/admin/news/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        newsServiceImp.deleteNews(id);
        redirect.addFlashAttribute("success", "Tin tức đã được xóa!");
        return "redirect:/admin/news";
    }
    @GetMapping("/admin/news/{id}/upload")
    public String getUpload(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("news", newsServiceImp.findOneNews(id));
        return "admin/news_upload";
    }

    @PostMapping(path = "/admin/news/{id}/upload")
    public String postUpload(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile imageFile,
                             RedirectAttributes redirect) {
        News news = newsServiceImp.findOneNews(id);
        newsServiceImp.upload(news, imageFile);

        redirect.addFlashAttribute("success", "Upload ảnh thành công!");
        return "redirect:/admin/news";
    }
}
