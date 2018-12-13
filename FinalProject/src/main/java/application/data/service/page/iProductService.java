package application.data.service.page;

import application.data.model.Product;

import java.util.List;

public interface iProductService {
    void addNewProduct(Product product);
    Product findOneProduct(int productId);
    List<Product> findAllProduct();
    void updateproduct(Product product);
    void deleteProduct(int id);
    List<Product> findbyName(String name);
    List<Product> listproductBycategory(int categoryId);
    List<Product> listProductHome();

}
