package site.xunyi.demo.redis.tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.xunyi.demo.redis.service.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
public class RedisTests {
    @Autowired
    private StringService stringService;
    @Autowired
    private HashService hashService;
    @Autowired
    private ListService listService;
    @Autowired
    private SetService setService;
    @Autowired
    private ZSetService zSetService;
    @Autowired
    private HyperLogLogService hyperLogLogService;
    @Autowired
    private GeoService geoService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void allIn() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("G:\\zy\\workspace\\demo\\redis\\commands")));

        String command;
        while((command = reader.readLine()) != null){
            String[] strings = command.split(" +");
            execute(strings);
        }
    }

    private void execute(String[] strings){
        Command command = Command.valueOf(strings[0].toUpperCase());
        chooseRedisService(command).execute(strings);
    }

    private RedisService chooseRedisService(Command command){
        RedisService redisService;
        switch(command) {
            case HASH:
                redisService = hashService;
                break;
            case LIST:
                redisService = listService;
                break;
            case SET:
                redisService = setService;
                break;
            case ZSET:
                redisService = zSetService;
                break;
            case HYPERLOGLOG:
                redisService = hyperLogLogService;
                break;
            case GEO:
                redisService = geoService;
                break;
            case STRING:
            default:
                redisService = stringService;
                break;
        }
        return redisService;
    }

    private enum Command{
        STRING,HASH,LIST,SET,ZSET,HYPERLOGLOG,GEO
    }

    @Test
    public void testAdd(){
        redisTemplate.executePipelined((RedisCallback) connection-> {
            for(int i = 0; i < 1000000; i++){
                connection.hashCommands().hSet("key".getBytes(), String.valueOf(i).getBytes(), ("xxxxxxxx" + i).getBytes());
            }
            return null;
        });
    }

    @Test
    public void testHashScan(){
        try (Cursor<Map.Entry<String, Object>> cursor = redisTemplate.opsForHash().scan("key", ScanOptions.scanOptions().match("*").count(1000).build())){
            while(cursor.hasNext()){
                AtomicInteger num = new AtomicInteger();
                cursor.forEachRemaining(entry -> {
                    num.incrementAndGet();
                });
                System.out.println(num);
            }
        }catch (IOException e){

        }

    }
}
