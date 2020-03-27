package site.xunyi.demo.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration
public class RedisScript {
    @Bean(name = "luaTest")
    public DefaultRedisScript testLuaScript(){
        DefaultRedisScript<String> script = new DefaultRedisScript<>();
        script.setResultType(String.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/lua/test.lua")));
        return script;
    }
}
