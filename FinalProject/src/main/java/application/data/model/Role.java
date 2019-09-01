package application.data.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tbl_role")
public class Role {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "role_id")
    private int role_id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String desc;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
