package application.data.repository.web;

import application.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface iProductRepository extends JpaRepository<Product,Integer>{
    List<Product> findByNameContaining(String name);

    @Query(value = "select  *from tbl_product where category_id = ? ", nativeQuery = true)
     List<Product> listproductBycategory(int categoryId);
}
