package application.data.service.page;

import application.constant.Constant;
import application.data.model.News;
import application.data.repository.web.iNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class NewsServiceImp implements NewsService {
    @Autowired
    private iNewsRepository newsRepository;

    @Override
    public ArrayList<News> findAllNews() {
        try {
            return newsRepository.getAllNews();
        }catch (Exception ex){
            ex.getMessage();
            return new ArrayList<>();
        }
    }
    public ArrayList<News> getNewnews() {
        try {
            return newsRepository.getAllNewNews();
        }catch (Exception ex){
            ex.getMessage();
            return new ArrayList<>();
        }
    }
    @Override
    public News findOneNews(int newsId) {
        return newsRepository.findOne(newsId);
    }
    @Override
    public News saveNews(News news) {
        return newsRepository.save(news);
    }
    @Override
    public void deleteNews(int newsId) {
        newsRepository.delete(newsId);
    }
    @Override
    @Transactional
    public News upload(News news, MultipartFile imageFile) {
        // Upload file to storage
        FileOutputStream fos = null;
        try {
            byte[] bytes = imageFile.getBytes();
            String fileName = imageFile.getOriginalFilename();
            String fileLocation = new File(Constant.UPLOAD_FOLDER).getAbsolutePath() + "\\" + fileName;
             fos = new FileOutputStream(fileLocation);
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Update image name in DB
        news.setImage(imageFile.getOriginalFilename());
        return newsRepository.save(news);
    }
    public ArrayList<News> getAll(){
        return newsRepository.getAllNews();
    }

}
