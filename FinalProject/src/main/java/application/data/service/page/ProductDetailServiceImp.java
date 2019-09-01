package application.data.service.page;

import application.data.model.ProductDetail;
import application.data.repository.web.iProductDetailRepository;
import application.data.repository.web.iProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductDetailServiceImp implements iProductDetailService {
    @Autowired
    private iProductDetailRepository iProductDetailRepository;

    @Override
    public ProductDetail findOne(int productdetailid) {
        return iProductDetailRepository.findOne(productdetailid);
    }

    @Override
    public void createDetail(ProductDetail productDetail) {
        iProductDetailRepository.save(productDetail);
    }


    @Transactional
    @Override
    public List<ProductDetail> findAllDetail() {
        return (List<ProductDetail>) iProductDetailRepository.findAll();
    }

    @Override
    public List<ProductDetail> findByProductId(int id) {
        return iProductDetailRepository.findByProductId(id);
    }

    @Override
    public void updateDetail(ProductDetail productDetail) {
        iProductDetailRepository.save(productDetail);
    }

    @Override
    public void delete(int id) {
        iProductDetailRepository.delete(id);
    }

    @Override
    public ProductDetail getByDetail(int productId, int sizeId, int colorId) {
        return iProductDetailRepository.getByDetail(productId,sizeId,colorId);
    }

    @Override
    public ProductDetail findproductDetailbyColor(int colorId, int productId) {
        return iProductDetailRepository.getImagebyColor(colorId,productId);
    }


}

