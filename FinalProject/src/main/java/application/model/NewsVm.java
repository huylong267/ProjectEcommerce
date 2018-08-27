package application.model;

import application.data.model.News;
import application.data.service.page.NewsServiceImp;

import java.util.ArrayList;
import java.util.Date;

public class NewsVm {
    private ArrayList<News> listNews;
    private ArrayList<News> listNewNews;

    public ArrayList<News> getListNews() {
        return listNews;
    }

    public void setListNews(ArrayList<News> listNews) {
        this.listNews = listNews;
    }

    public ArrayList<News> getListNewNews() {
        return listNewNews;
    }

    public void setListNewNews(ArrayList<News> listNewNews) {
        this.listNewNews = listNewNews;
    }
}
