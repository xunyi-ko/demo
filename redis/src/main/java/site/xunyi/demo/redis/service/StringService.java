package site.xunyi.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class StringService extends RedisService{
    @Autowired
    private RedisTemplate redisTemplate;

    private ValueOperations valueOperations;
    @Override
    protected Object getOperations() {
        if(valueOperations == null){
            this.valueOperations = redisTemplate.opsForValue();
        }
        return this.valueOperations;
    }
}
