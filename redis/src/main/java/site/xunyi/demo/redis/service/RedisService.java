package site.xunyi.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public abstract class RedisService {
    @Autowired
    protected RedisTemplate redisTemplate;

    protected abstract Object getOperations();

    public void execute(String[] strings){
        String methodName = strings[1];

        Class[] parameterTypes = new Class[strings.length - 2];
        String[] parameters = new String[strings.length - 2];
        for(int i = 0; i < strings.length - 2; i++){
            parameterTypes[i] = Object.class;
            parameters[i] = strings[i + 2];
        }
        try {
            Method method = getOperations().getClass().getMethod(methodName, parameterTypes);
            method.setAccessible(true);
            Object invoke = method.invoke(getOperations(), parameters);

            System.out.println(invoke);
        } catch (NoSuchMethodException e) {
            System.out.println("no such method " + methodName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
