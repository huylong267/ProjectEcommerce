package application.data.service.page;

import application.data.model.ProductDetail;

import java.util.List;

public interface iProductDetailService {
    ProductDetail findOne(int productdetailid);
    void createDetail (ProductDetail productDetail);
    List<ProductDetail> findAllDetail();
    List<ProductDetail> findByProductId(int id);
    void delete(int id);
}
