package application.data.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity(name = "tbl_discount")
public class Discount {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "discount_id")
    private int discount_id;
    @Column(name = "name")
    private String name;
    @Column(name = "start_date")
    private Date start_date;
    @Column(name = "end_date")
    private Date end_date;
    @Column(name = "description")
    private String desc;
    @Column(name = "image")
    private String image;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_discountdetail",
            joinColumns =@JoinColumn(name = "discount_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
