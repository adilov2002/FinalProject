package com.example.FinalProject.repositories;

import com.example.FinalProject.entities.Categories;
import com.example.FinalProject.entities.News;
import com.example.FinalProject.entities.NewsType;
import com.example.FinalProject.entities.Places;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

@Stateless
public class NewsRepository {

    private Repository repository = new Repository();

    @EJB
    private NewsTypeRepository newsTypeRepository;

    private Connection connection = repository.getConnection();

    public ArrayList<News> getAllNews(){
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from t_news");
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Long id = set.getLong("id");
                String content = set.getString("content");
                LocalDate date = set.getDate("published_date").toLocalDate();
                String title = set.getString("title");
                Long newsTypeId = set.getLong("newstype_id");
                NewsType type = newsTypeRepository.getNewsTypeById(newsTypeId);
                news.add(new News(id, title, content, date, type));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public ArrayList<News> getNewsByTypeId(Long id){
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from t_news where newstype_id = ? ");
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Long id1 = set.getLong("id");
                String content = set.getString("content");
                LocalDate date = set.getDate("published_date").toLocalDate();
                String title = set.getString("title");
                Long newsTypeId = set.getLong("newstype_id");
                NewsType type = newsTypeRepository.getNewsTypeById(newsTypeId);
                news.add(new News(id, title, content, date, type));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }
}
