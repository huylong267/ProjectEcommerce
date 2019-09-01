package application.data.repository.auth;


import application.data.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface iUserRoleRepository extends JpaRepository<UserRole,Integer> {

    @Query("select u from tbl_user_role u")
    ArrayList<UserRole> findUserRole();
    @Query("select r from tbl_user_role r where r.user_id=:userId")
    UserRole findRoleIdByUserId(@Param("userId") int userId);
}
