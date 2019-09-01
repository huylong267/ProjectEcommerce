package application.data.repository.auth;

import application.data.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface iRoleRepository extends CrudRepository<Role,Integer>{

    Role findByName(String name);

    @Query("select r from tbl_role r where r.role_id = :id")
    Role findRoleById(@Param("id")int id);

}
