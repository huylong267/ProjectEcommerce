package application.social;

import application.data.model.Role;
import application.data.model.User;
import application.data.repository.auth.iRoleRepository;
import application.data.repository.auth.iUserRepository;
import application.data.service.auth.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private iRoleRepository roleRepository;

    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword(randomAlphabetic(8));
        user.setEmail(null);
        user.setName(null);
        user.setAddress(null);
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userServiceImp.register(user);
        return user.getUsername();
    }
}
