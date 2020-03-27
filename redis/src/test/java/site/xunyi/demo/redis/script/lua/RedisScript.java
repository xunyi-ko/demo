package site.xunyi.demo.redis.script.lua;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisScript {
    @Autowired
    @Qualifier(value = "luaTest")
    private DefaultRedisScript<String> luaTest;

    @Autowired
    @Qualifier("myRedisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    public void luaTest(){

        int size = 1000;
        String fake = "123456789123asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf123456789123asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf123456789123asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf123456789123asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf";
        String[] array = new String[size];
        for(int i = 0; i < size; i++){
            array[i] = fake;
        }

        long start = System.currentTimeMillis();
        Object o = redisTemplate.execute(luaTest, Collections.EMPTY_LIST, array);
        System.out.println(o);

        System.out.println(System.currentTimeMillis() - start);
    }
}
