package application.data.repository.web;

import application.data.model.ProductDetail;
import application.model.ProductDataModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface iProductDetailRepository extends CrudRepository<ProductDetail,Integer> {
    @Query(value = "select * from tbl_productdetail pdt where pdt.product_id = :productid",nativeQuery = true)
    List<ProductDetail> findByProductId(@Param("productid") int productid);

    @Query(value = "select * from  tbl_productdetail p where p.product_id=:productId " +
            "and p.size_id =:sizeId and p.color_id =:colorId ",nativeQuery = true)
    ProductDetail getByDetail (@Param("productId") int productId , @Param("sizeId") int sizeId, @Param("colorId") int colorId);

    @Query(value = "select p.image from tbl_productdetail p where p.color_id=:colorId",nativeQuery = true)
    ProductDetail getImagebyColor (@Param("colorId")int colorId);
}



