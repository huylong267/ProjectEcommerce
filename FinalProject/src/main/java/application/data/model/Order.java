package application.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_order")
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "order_id")
    private int order_id;
    @Column(name = "address")
    private String address;
    @Column(name ="phone" )
    private int phone;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private int status;
    @Column(name = "created_date")
    private Date created_date;
    @Column(name = "totalprice")
    private int totalprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
