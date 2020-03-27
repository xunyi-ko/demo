package site.xunyi.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

@Service
public class SetService extends RedisService{
    @Autowired
    private RedisTemplate redisTemplate;

    private SetOperations setOperations;

    @Override
    protected Object getOperations() {
        if(setOperations == null){
            this.setOperations = redisTemplate.opsForSet();
        }
        return setOperations;
    }
}
