package application.model;

public class ProductDetailEditModel {

    private int color_id;
    private int size_id;
    private int quantity;
    private String image;



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
