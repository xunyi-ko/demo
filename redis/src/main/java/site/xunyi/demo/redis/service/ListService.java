package site.xunyi.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ListService extends RedisService{
    @Autowired
    private RedisTemplate redisTemplate;

    private ListOperations listOperations;

    @Override
    protected Object getOperations() {
        if(listOperations == null){
            this.listOperations = redisTemplate.opsForList();
        }
        return listOperations;
    }
}
