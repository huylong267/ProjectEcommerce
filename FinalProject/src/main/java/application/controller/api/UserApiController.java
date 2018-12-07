package application.controller.api;


import application.data.model.Role;
import application.data.model.User;
import application.data.model.UserRole;
import application.data.service.auth.UserServiceImp;
import application.model.BaseApiResult;
import application.model.DataApiResult;
import application.viewmodel.UserDataModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.viewmodel.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/list-customers")
    public BaseApiResult listCustomers() {
        DataApiResult result = new DataApiResult();
        ModelMapper modelMapper = new ModelMapper();
        try {
            ArrayList<User> users = userServiceImp.getAll();
            ArrayList<UserDataModel> userDataModels =new ArrayList<>();
            for(User u : users){
                userDataModels.add(modelMapper.map(u,UserDataModel.class));
            }
            result.setData(userDataModels);
            result.setMessage("success");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setData(null);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping(path="/user/{id}")
    public BaseApiResult user(@PathVariable int id) {
        DataApiResult result = new DataApiResult();
        try{
            User user = userServiceImp.findOne(id);
            if(user == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                result.setSuccess(true);
                ModelMapper modelMapper = new ModelMapper();
                UserDataModel userDataModel =
                        modelMapper.map(user, UserDataModel.class);
                result.setData(userDataModel);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;

    }
    @GetMapping(path = "/alluser")
    public BaseApiResult userRole(){
        DataApiResult result = new DataApiResult();
        ModelMapper modelMapper = new ModelMapper();
        try {
            ArrayList<UserRoleDataModel> userRoleDataModels = new ArrayList<>();
            ArrayList<UserRole> userRole = userServiceImp.findUserRole();
            ArrayList<UserRoleDetailModel> userRoleDetailModels = new ArrayList<>();
            for(UserRole u : userRole){
                userRoleDetailModels.add(modelMapper.map(u,UserRoleDetailModel.class));
            }
            for(UserRoleDetailModel u : userRoleDetailModels){
                User user = userServiceImp.findOne(u.getUser_id());
                UserDataModel userDataModel=modelMapper.map(user,UserDataModel.class);
                Role role =userServiceImp.findOneRole(u.getRole_id());
                RoleDataModel roleDataModel = modelMapper.map(role,RoleDataModel.class);
                userRoleDataModels.add(new UserRoleDataModel(u.getId(),userDataModel,roleDataModel));
            }
            result.setData(userRoleDataModels);
            result.setMessage("success");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setData(null);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }


        return result;
    }
    @PostMapping("/update-role/")
    public BaseApiResult updateRole(@RequestBody UserDataModel userDataModel){
        DataApiResult result = new DataApiResult();
        try {
            if(userServiceImp.updateRole(userDataModel.getUserId())){
                result.setMessage("success");
                result.setSuccess(true);
                result.setData(null);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            result.setData(null);
        }

        return result;
    }
    @GetMapping("/update-user/{username}")
    public BaseApiResult updateUser(@PathVariable String username,@RequestBody UserDataModel userDataModel){
        DataApiResult result = new DataApiResult();
        try{
           User userExist = userServiceImp.findByUsername(username);
           if(userExist == null ){
               result.setSuccess(false);
               result.setMessage("user null");
           }else {
               userExist.setAddress(userDataModel.getAddress());
               userExist.setAvatar(userDataModel.getAvatar());
               userExist.setDob(userDataModel.getDob());
               userExist.setEmail(userDataModel.getEmail());
               userExist.setGender(userDataModel.getGender());
               userExist.setPassword(userDataModel.getPassword());
               userServiceImp.register(userExist);
               result.setData(userDataModel);
               result.setMessage("update successfull");
               result.setSuccess(true);
           }
        }catch (Exception ex){
            result.setMessage("catch update user");
            result.setSuccess(false);
        }
        return result;
    }

}
