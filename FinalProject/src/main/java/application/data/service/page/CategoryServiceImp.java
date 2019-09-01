package application.data.service.page;

import application.data.model.Category;
import application.data.repository.web.iCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements iCategoryService {

    @Autowired
    private iCategoryRepository categoryRepository;


    @Override
    public List<Category> findAllCate() {
        try {
            return (List<Category>) categoryRepository.findAll();
        }catch (Exception ex){
            ex.getMessage();
            return new ArrayList<>();
        }
    }

    @Override
    public Category findOneCate(int categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public Category saveCate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCate(int categoryId) {
        categoryRepository.delete(categoryId);
    }
}
