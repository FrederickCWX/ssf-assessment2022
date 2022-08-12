package com.vttp2022.SSFAssessment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.vttp2022.SSFAssessment.models.Data;

import java.util.Optional;

import org.slf4j.*;

@Configuration
public class RedisConfig {
  private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

  @Value("${spring.redis.host}")
    private String redisHost;

  @Value("${spring.redis.port}")
  private Optional<Integer> redisPort;

  //@Value("${spring.redis.password}")
  //private String redisPassword;

  @Value("${spring.redis.database}")
  private String redisDatabase;

  @Bean(name = "news")
  @Scope("singleton")
  public RedisTemplate<String, Data> redisTemplate(){
    final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
    config.setHostName(redisHost);
    config.setPort(redisPort.get());
    config.setPassword(System.getenv("CRYPTOCOMPARE_NEWS_REDIS_PASSWORD"));
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Data.class);

    final JedisClientConfiguration jedisCli = JedisClientConfiguration.builder().build();
    final JedisConnectionFactory jedisFact = new JedisConnectionFactory(config, jedisCli);
    jedisFact.afterPropertiesSet();
    
    RedisTemplate<String, Data> temp = new RedisTemplate<String, Data>();
    temp.setConnectionFactory(jedisFact);
    temp.setKeySerializer(new StringRedisSerializer());
    temp.setValueSerializer(jackson2JsonRedisSerializer);
    temp.setHashKeySerializer(temp.getKeySerializer());
    temp.setHashValueSerializer(temp.getValueSerializer());
    return temp;
  }

  
}
