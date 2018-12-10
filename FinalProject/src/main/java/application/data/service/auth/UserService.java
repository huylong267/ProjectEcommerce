package application.data.service.auth;

import application.data.model.User;
import application.data.model.UserRole;

import java.util.ArrayList;
import java.util.List;



public interface UserService {

    List<User> findAll();

    User findOne(Integer id);

    long countAll();

    void delete(Integer id);

    boolean register(User user);

    ArrayList<UserRole> findUserRole();

    User findByUsername(String username);
     User update(User user);


}