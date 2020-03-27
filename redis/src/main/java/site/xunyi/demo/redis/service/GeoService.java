package site.xunyi.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class GeoService extends RedisService{
    @Autowired
    private RedisTemplate redisTemplate;

    private GeoOperations geoOperations;

    @Override
    protected Object getOperations() {
        if(geoOperations == null){
            this.geoOperations = redisTemplate.opsForGeo();
        }
        return geoOperations;
    }
}
