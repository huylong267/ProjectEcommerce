package application.data.repository.web;

import application.data.model.PaginableItemList;
import application.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface iProductRepository extends JpaRepository<Product,Integer>{
    List<Product> findByNameContaining(String name);

    @Query(value = "select  *from tbl_product where category_id = ? ", nativeQuery = true)
     List<Product> listproductBycategory(int categoryId);

    @Query(value = "select  * from tbl_product  where category_id = ? LIMIT 6",nativeQuery = true)
    List<Product> listProductHome(int categoryId);


    @Query(value = "select * from tbl_product p where p.category_id= :category_id  ORDER BY ?#{#pageable}" ,nativeQuery = true)
    Page<Product> listProductPaging( @Param("category_id") Integer category_id, Pageable  pageable);



}
