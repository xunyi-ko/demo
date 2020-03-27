package site.xunyi.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
public class ZSetService extends RedisService{
    @Autowired
    private RedisTemplate redisTemplate;

    private ZSetOperations zSetOperations;

    @Override
    protected Object getOperations() {
        if(zSetOperations == null){
            this.zSetOperations = redisTemplate.opsForZSet();
        }
        return zSetOperations;
    }
}
