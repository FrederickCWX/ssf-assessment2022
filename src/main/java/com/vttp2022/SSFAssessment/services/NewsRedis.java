
package com.vttp2022.SSFAssessment.services;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.vttp2022.SSFAssessment.models.Data;

@Service
public class NewsRedis implements NewsRepo{
  private static final Logger logger = LoggerFactory.getLogger(NewsRedis.class);

  @Autowired
  @Qualifier("news")
  RedisTemplate<String, Data> redisTemp;

  @Override
  public int save(final Data data){
    logger.info("Save news data");
    redisTemp.opsForValue().set(data.getId(), data);
    Data result = (Data) redisTemp.opsForValue().get(data.getId());
    if(result != null){
      return 1;
    }
    return 0;
  }

  @Override
  public Data findById(final String newsId){
    logger.info("find news by id");
    Data result = (Data) redisTemp.opsForValue().get(newsId);
    return result;
  }
  
}
