package application.data.repository.web;

import application.data.model.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


public interface iNewsRepository extends CrudRepository<News,Integer> {
    @Transactional(readOnly = true)
    @Query(value = "select * from tbl_news order by news_id desc limit 3",nativeQuery = true)
    ArrayList<News> getAllNewNews();
    @Query(value = "select u from tbl_news u")
    ArrayList<News> getAllNews();
}
