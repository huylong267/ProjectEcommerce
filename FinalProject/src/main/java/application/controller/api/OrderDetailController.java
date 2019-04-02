package application.controller.api;


import application.data.model.OrderDetail;
import application.data.service.page.OrderDetailService;
import application.model.BaseApiResult;
import application.model.DataApiResult;
import application.model.OrderDetailModel;
import org.modelmapper.ModelMapper;
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

    @PostMapping("/create")
    public BaseApiResult createOrderDetail(@RequestBody OrderDetailModel orderModel){
        DataApiResult result =  new DataApiResult();
        try {
            if(!"".equals(orderModel.getAmount()) &&  !"".equals(orderModel.getPrice())){
                ModelMapper mapper = new ModelMapper();
                OrderDetail orderDetail = mapper.map(orderModel,OrderDetail.class);
                orderDetailService.createOrderDetail(orderDetail);
                result.setSuccess(true);
                result.setMessage("create successfully");
                result.setData(orderDetail);
            }else {
                result.setSuccess(false);
                result.setMessage("create fail");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("create fail");
        }
        return result;
    }
}
