package application.viewmodel;

import application.data.model.Role;
import application.data.model.User;

public class UserRoleDataModel {
    private int id;
    private UserDataModel user;
    private RoleDataModel role;

    public UserRoleDataModel(int id, UserDataModel user, RoleDataModel role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public UserRoleDataModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDataModel getUser() {
        return user;
    }

    public void setUser(UserDataModel user) {
        this.user = user;
    }

    public RoleDataModel getRole() {
        return role;
    }

    public void setRole(RoleDataModel role) {
        this.role = role;
    }
}
