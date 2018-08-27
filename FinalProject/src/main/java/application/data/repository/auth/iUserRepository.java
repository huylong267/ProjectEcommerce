package application.data.repository.auth;

import application.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface iUserRepository extends JpaRepository<User,Integer> {
    @Transactional(readOnly = true)
    @Query("select u from tbl_user u where u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("select u from tbl_user u")
    ArrayList<User> getAll();

    @Query("select u from tbl_user u where u.userId = :id")
    User findUserById(@Param("id") int id);


}

