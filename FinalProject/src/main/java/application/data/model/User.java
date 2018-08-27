package application.data.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "tbl_user")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name ="user_id")
    private int userId;

    public User() {
    }

    public User(String username, String password, String avatar, Date dob, String email, String address, int gender, String name, Date createdDate, Date updateDate, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.name = name;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.roles = roles;
    }

    @Column(name ="username")
    private String username;
    @Column(name = "password_hashed")
    private String password;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "gender")
    private int gender;
    @Column(name = "name")
    private String name;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updateDate;

    @ManyToMany
    @JoinTable(
            name="tbl_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }



    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
