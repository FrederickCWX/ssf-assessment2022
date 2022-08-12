package com.vttp2022.SSFAssessment.services;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vttp2022.SSFAssessment.models.News;

@Service
public class NewsService {
  private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

  private static String URL = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";

  public Optional<News> getArticles(){
    String apiKey = System.getenv("CRYPTOCOMPARE_NEWS_API_KEY");

    RestTemplate temp = new RestTemplate();
    ResponseEntity<String> resp = null;

    try{
      HttpHeaders headers = new HttpHeaders();
      headers.set("Apikey", apiKey);
      HttpEntity request = new HttpEntity(headers);

      resp = temp.exchange(URL, HttpMethod.GET, request, String.class, 1);

      News n = News.createJson(resp.getBody());
      return Optional.of(n);
    }catch(Exception e){
      logger.error(e.getMessage());
      e.printStackTrace();
    }
    return Optional.empty();
  }
  
}
