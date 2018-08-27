package application.model;

import application.data.model.Color;
import application.data.model.Product;
import application.data.model.Size;
import application.extension.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class ProductDetailModel {

    private ProductNameModel product;
    private SizeNameModel size;
    private ColorNameModel color;
    private int quantity;

    public ProductNameModel getProduct() {
        return product;
    }

    public void setProduct(ProductNameModel product) {
        this.product = product;
    }

    public SizeNameModel getSize() {
        return size;
    }

    public void setSize(SizeNameModel size) {
        this.size = size;
    }

    public ColorNameModel getColor() {
        return color;
    }

    public void setColor(ColorNameModel color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
