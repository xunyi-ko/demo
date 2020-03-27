package site.xunyi.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class HashService extends RedisService{
    @Autowired
    private RedisTemplate redisTemplate;

    private HashOperations hashOperations;

    @Override
    protected Object getOperations() {
        if(hashOperations == null){
            this.hashOperations = redisTemplate.opsForHash();
        }
        return hashOperations;
    }
}
