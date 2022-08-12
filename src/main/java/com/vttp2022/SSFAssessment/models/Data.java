package com.vttp2022.SSFAssessment.models;

import org.slf4j.*;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;

public class Data {
  private static final Logger logger = LoggerFactory.getLogger(Data.class);

  private String id;
  private int publishedOn;
  private String title;
  private String url;
  private String imageUrl;
  private String body;
  private String tags;
  private String categories;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getPublished_on() {
    return publishedOn;
  }
  public void setPublished_on(int publishedOn) {
    this.publishedOn = publishedOn;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getImageUrl() {
    return imageUrl;
  }
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  public String getTags() {
    return tags;
  }
  public void setTags(String tags) {
    this.tags = tags;
  }
  public String getCategories() {
    return categories;
  }
  public void setCategories(String categories) {
    this.categories = categories;
  }

  public static Data createJson(JsonObject jo){
    Data d = new Data();
    
    JsonString jsId = jo.getJsonString("id");
    d.id = jsId.getString();
    JsonNumber jnPublishedOn = jo.getJsonNumber("published_on");
    d.publishedOn = jnPublishedOn.intValue();
    
    JsonString jsTitle = jo.getJsonString("title");
    d.title = jsTitle.getString();
    JsonString jsUrl = jo.getJsonString("url");
    d.url = jsUrl.getString();
    JsonString jsImageUrl = jo.getJsonString("imageurl");
    d.imageUrl = jsImageUrl.getString();
    JsonString jsBody = jo.getJsonString("body");
    d.body = jsBody.getString();
    JsonString jsTags = jo.getJsonString("tags");
    d.tags = jsTags.getString();
    JsonString jsCategories = jo.getJsonString("categories");
    d.categories = jsCategories.getString();

    return d;
  }
  
}
