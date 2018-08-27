package application.data.service.page;

import application.data.model.Category;

import java.util.List;

public interface iCategoryService {
    List<Category>findAllCate();
    Category findOneCate(int categoryId);
    Category saveCate(Category category);
    void deleteCate(int categoryId);

}
