package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_orderdetail")
public class OrderDetail {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "orderdetail_id")
    private int orderdetail_id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "productdetail_id")
    private ProductDetail productDetail;

    @Column(name = "amount")
    private int amount;
    @Column(name = "price")
    private  int price;

    public int getOrderdetail_id() {
        return orderdetail_id;
    }

    public void setOrderdetail_id(int orderdetail_id) {
        this.orderdetail_id = orderdetail_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

//    public ProductDetail getProductDetail() {
//        return productDetail;
//    }
//
//    public void setProductDetail(ProductDetail productDetail) {
//        this.productDetail = productDetail;
//    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
