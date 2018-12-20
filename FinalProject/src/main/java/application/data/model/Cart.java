package application.data.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Table(name = "tbl_cart")
public class Cart {
    @Column(name = "cartid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
    @Column(name = "productdetail_id")
    private int productDetailId;
    @Column(name = "guid")
    private String guId ;
    @Column(name = "amount")
    private int  amount;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getGuId() {
        return guId;
    }

    public void setGuId(String guId) {
        this.guId = guId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
