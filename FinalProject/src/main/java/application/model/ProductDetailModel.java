package application.model;

public class ProductDetailModel {
    private int productdetail_id;
    private ProductNameModel product;
    private SizeNameModel sizeDetail;
    private ColorNameModel colorDetail;
    private int quantity;
    private String image;



    public void setProductDetail_id(int productDetail_id) {
        this.productdetail_id = productDetail_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductNameModel getProduct() {
        return product;
    }

    public void setProduct(ProductNameModel product) {
        this.product = product;
    }

    public int getProductdetail_id() {
        return productdetail_id;
    }

    public void setProductdetail_id(int productdetail_id) {
        this.productdetail_id = productdetail_id;
    }

    public SizeNameModel getSizeDetail() {
        return sizeDetail;
    }

    public void setSizeDetail(SizeNameModel sizeDetail) {
        this.sizeDetail = sizeDetail;
    }

    public ColorNameModel getColorDetail() {
        return colorDetail;
    }

    public void setColorDetail(ColorNameModel colorDetail) {
        this.colorDetail = colorDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDetailModel{" +
                "productDetail_id=" + productdetail_id +
                ", product=" + product +
                ", size=" + sizeDetail +
                ", color=" + colorDetail +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                '}';
    }
}
