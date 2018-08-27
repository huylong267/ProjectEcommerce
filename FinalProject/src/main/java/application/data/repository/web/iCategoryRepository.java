package application.data.repository.web;

import application.data.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface iCategoryRepository extends CrudRepository<Category,Integer> {


}
