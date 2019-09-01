package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_user_role")
public class UserRole {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "user_role_id")
    private int id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "role_id")
    private int role_id;
    @Column(name = "status")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
