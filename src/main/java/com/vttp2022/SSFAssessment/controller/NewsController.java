package com.vttp2022.SSFAssessment.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vttp2022.SSFAssessment.models.News;
import com.vttp2022.SSFAssessment.models.Data;
import com.vttp2022.SSFAssessment.services.NewsService;

@Controller
public class NewsController {
  private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

  @Autowired
  private NewsService newsSvc;

  @GetMapping("/")
  public String newsPage(Model model){
    Optional<News> optNews = newsSvc.getArticles();

    if(optNews.isEmpty()){
      model.addAttribute("news", new News());
      return "news";
    }
    List<Data> datas = News.getData();
    model.addAttribute("datas", datas);

    return "news";

  }
  
}
