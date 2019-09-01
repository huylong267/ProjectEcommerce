package application.data.repository.web;

import application.data.model.OrderDetail;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

        import java.util.ArrayList;


public interface iOrderDetailRepository extends JpaRepository<OrderDetail,Integer> {


//    @Query(value = "select op from tbl_orderproduct op where op.orderid =:orderid",nativeQuery = true)
//    ArrayList<OrderDetail> getAllByOrder(@Param("orderid")int orderid);
//
//    @Query(value = "select op from tbl_orderproduct op where op.productid = :productid and op.orderid = :orderid",nativeQuery = true)
//    OrderDetail findOrderProductByProductidAndOrderid(@Param("productid") int productid,
//                                                       @Param("orderid") int orderid);
//
//    @Query(value = "select op from tbl_orderproduct op where op.orderid = :orderid",nativeQuery = true)
//    ArrayList<OrderDetail> getAllByOrderid(@Param("orderid") int orderid);

//    @Query("delete  from tbl_orderproduct op where op.productid = :productid")
//    void deleteByProductid(@Param("productid")int productid);
}
