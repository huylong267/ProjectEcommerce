package application.data.service.page;


import application.data.model.OrderDetail;
import application.data.repository.web.iOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImp implements OrderDetailService{
    @Autowired
    private iOrderDetailRepository iOrderDetailRepository;


    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return  iOrderDetailRepository.save(orderDetail);
    }
}
