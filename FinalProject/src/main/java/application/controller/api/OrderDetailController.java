package application.controller.api;


import application.data.service.page.OrderDetailService;
import application.model.BaseApiResult;
import application.model.DataApiResult;
import application.model.OrderDetailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderdetail/")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

//    @PostMapping("/create")
//    public BaseApiResult createOrderDetail(@RequestBody OrderDetailModel orderModel){
//        DataApiResult result =  new DataApiResult();
//        if(!orderModel.get.equals(""))
//    }
}
