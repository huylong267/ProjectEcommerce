package application.data.service.auth;

import application.constant.RoleIdConstant;
import application.data.model.Role;
import application.data.model.User;
import application.data.model.UserRole;
import application.data.repository.auth.iUserRepository;
import application.data.repository.auth.iRoleRepository;
import application.data.repository.auth.iUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class  UserServiceImp implements UserService {

    @Autowired
    private iUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private iRoleRepository iRoleRepository;

    @Autowired
    private iUserRoleRepository iuserroleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    public Role findOneRole(int id){
        return roleRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countAll() {
        return userRepository.count();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    public String findIdByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user.getUser_id()+"";
    }
    @Override
    @Transactional
    public boolean register(User user) {
        // Check email exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
//        if(user.getAvatar() == null){
//            user.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTbL6R7NvFBbxyPjJE4OLWxOCNWoy345q73fotyDRCaNsc9bMe");
//        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedDate(new Date());
        user.setUpdateDate(new Date());
        HashSet<Role> role = new HashSet<>();
        role.add(iRoleRepository.findByName("ROLE_USER"));
        userRepository.save(user);

        return true;
    }

    @Override
    public ArrayList<UserRole> findUserRole() {
        return iuserroleRepository.findUserRole();
    }


    public ArrayList<User> getAll(){
        return userRepository.getAll();
    }
//
//    public User findUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }

    public User findUserById(int id){
        return userRepository.findUserById(id);
    }

    @Autowired
    private iRoleRepository roleRepository;
    public Role findRoleById(int id){
        return roleRepository.findRoleById(id);
    }
    @Autowired
    private iUserRoleRepository userRoleRepository;
    public boolean updateRole(int userId){
        try {
            UserRole userRole  = userRoleRepository.findRoleIdByUserId(userId);
            if(userRole.getRole_id() == RoleIdConstant.Role_Admin){
                userRole.setRole_id(RoleIdConstant.Role_User);
            }else if(userRole.getRole_id() == RoleIdConstant.Role_User){
                userRole.setRole_id(RoleIdConstant.Role_Admin);
            }
            userRoleRepository.save(userRole);
            return true;
        } catch (Exception e) {
//            logger.error(e.getMessage());
        }
        return false;
    }


}
