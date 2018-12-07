package application.controller.admin;


import application.data.model.Role;
import application.data.model.User;
import application.data.repository.auth.iRoleRepository;
import application.data.repository.auth.iUserRepository;
import application.data.service.auth.UserServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.sql.Date;
import java.util.HashSet;

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
                redirect.addFlashAttribute("msg","Thêm mới thành công");
            }else {
                redirect.addFlashAttribute("msg","Trùng Username");
            }


            return "redirect:/register";

    }

}
