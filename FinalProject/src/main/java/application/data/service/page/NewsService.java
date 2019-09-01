package application.data.service.page;

import application.data.model.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    List<News> findAllNews();
    News findOneNews(int newsId);
    News saveNews(News news);
    void deleteNews(int newsId);
    News upload(News news, MultipartFile imageFile);
}
