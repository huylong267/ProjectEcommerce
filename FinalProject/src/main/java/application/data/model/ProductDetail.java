package application.data.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_productdetail")
public class ProductDetail {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "productdetail_id")
    private int productdetail_id;


    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "size_id")
    private Size size;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id")
    private Color color;
    @Column(name = "quantity")
    private int quantity;



    public int getProductdetail_id() {
        return productdetail_id;
    }

    public void setProductdetail_id(int productdetail_id) {
        this.productdetail_id = productdetail_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
