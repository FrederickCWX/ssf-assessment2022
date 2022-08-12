package com.vttp2022.SSFAssessment.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class News {
  private static final Logger logger = LoggerFactory.getLogger(News.class);

  private static List<Data> data = new ArrayList();

  public static List<Data> getData(){
    return data;
  }
  
  public void setData(List<Data> data){
    this.data = data;
  }

  public static News createJson(String json) throws IOException{
    logger.info("News json");
    News n = new News();

    try(InputStream in = new ByteArrayInputStream(json.getBytes())){
      JsonReader jr = Json.createReader(in);
      JsonObject jo = jr.readObject();
      //logger.info(">>>>> " + jo.getJsonArray("Data"));
      JsonArray ja = jo.getJsonArray("Data");

      if(ja != null){
        List<Data> result = new ArrayList<>();
        for(Object jd: ja){
          JsonObject joData = (JsonObject) jd;
          result.add(Data.createJson(joData));
        }
        logger.info("Data json created");
        News.data = result;
      }
    }
    return n;
  }


  
}
