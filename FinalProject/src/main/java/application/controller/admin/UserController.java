package application.controller.admin;


import application.data.model.Role;
import application.data.model.User;
import application.data.repository.auth.iRoleRepository;
import application.data.repository.auth.iUserRepository;
import application.data.service.auth.UserServiceImp;
import application.data.service.page.CategoryServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private iUserRepository userRepository;
    @Autowired
    private iRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @GetMapping("admin/customer")
    public String customers() {
        return"admin/customer_list";
    }
    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
         model.addAttribute("categories", categoryServiceImp.findAllCate());
            model.addAttribute("user",new User());
        return "register";
    }
    @GetMapping("/admin")
    public String admin(){
        return "/admin/layout";
    }
    @GetMapping("/403")
    public String denied(){
        return "403";
    }
    @GetMapping("/fakeadmin")
    public String fake(){
        User user = new User();
        user.setUsername("admin");
        user.setGender(1);
        user.setPassword(passwordEncoder.encode("admin"));
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_ADMIN"));
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
        return "login";
    }
    @PostMapping("/register/save")
    public String newsSave(@RequestParam String name,@RequestParam String username,@RequestParam String password,
                           @RequestParam String  address,@RequestParam String email,
                          RedirectAttributes redirect){

            User user = new User();
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setUsername(username);
            user.setAddress(address);
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_USER"));
            user.setRoles(roles);
            if( userServiceImp.register(user)){
                redirect.addFlashAttribute("msg"," Thêm mới thành công");
            }else {
                redirect.addFlashAttribute("msg"," Thêm mới thất bại trùng username");
            }


            return "redirect:/register";

    }

    @GetMapping(path = "/edituser")
    public String  editUserGet (Model model){
        model.addAttribute("categories", categoryServiceImp.findAllCate());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
         User user = userServiceImp.findByUsername(((UserDetails) principal).getUsername());
            model.addAttribute("user",user);
            model.addAttribute("username",user.getUsername());
        }
        return "edituser";
    }

    @PostMapping(path = "/edituserpost")
    public String editUserPost(@RequestParam Map<String, Object> params,RedirectAttributes attributes ,Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            User user = (User) userServiceImp.findByUsername(String.valueOf(params.get("username")));
            if(user != null ){
                ModelMapper mapper = new ModelMapper();
                User userUpdated =mapper.map(params,User.class);
                userUpdated.setAvatar((String) params.get("avatar"));
                userUpdated.setUser_id(Integer.parseInt((String) params.get("userId")));
                HashSet<Role> roles = new HashSet<>();
                roles.add(roleRepository.findByName("ROLE_USER"));
                userUpdated.setRoles(roles);
                userUpdated.setPassword(passwordEncoder.encode((CharSequence) params.get("password")));
                userUpdated.setUpdateDate(new Date());
                model.addAttribute("username",user.getUsername());
                userServiceImp.update(userUpdated);
                attributes.addFlashAttribute("msgupdate","Cập nhật thành công");
        }
            else {
                attributes.addFlashAttribute("msgupdate","Cập nhật thất bại");
            }

        }
        return "redirect:/edituser";
    }
}
