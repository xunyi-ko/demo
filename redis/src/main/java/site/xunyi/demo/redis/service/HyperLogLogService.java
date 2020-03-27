package site.xunyi.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class HyperLogLogService extends RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    private HyperLogLogOperations hyperLogLogOperations;
    @Override
    protected Object getOperations() {
        if(hyperLogLogOperations == null){
            this.hyperLogLogOperations = redisTemplate.opsForHyperLogLog();
        }
        return hyperLogLogOperations;
    }
}
