package com.vttp2022.SSFAssessment.controller;

import org.slf4j.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vttp2022.SSFAssessment.models.Data;
import com.vttp2022.SSFAssessment.services.NewsRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/news",
consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {
  private static final Logger logger = LoggerFactory.getLogger(NewsRESTController.class);

  @Autowired
  NewsRedis newsrds;

  @GetMapping(path = "/{newsId}")
  public ResponseEntity<Data> getNewsById(@PathVariable String newsId){
    Data d = newsrds.findById(newsId);
    return ResponseEntity.ok(d);
  }

  
}
