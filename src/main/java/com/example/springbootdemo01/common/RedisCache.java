package com.example.springbootdemo01.common;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

//泛型类的使用
public class RedisCache<T> {

    //建立日志打印对象，例如每个函数里面的LOGGER.error(),LOGGER.debug()
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);

    //Spring封装了RedisTemplate对象来进行对Redis的各种操作，它支持所有的Redis原生的api
    //模板中的Redis key的类型（通常为String）如：RedisTemplate<String, Object>
    //如果没特殊情况，切勿定义成RedisTemplate<Object, Object>,因为这种定义方式容易导致使用时类型错误
    /**RedisTemplate中定义了对5种数据结构操作：
     redisTemplate.opsForValue();//操作字符串
     redisTemplate.opsForHash();//操作hash
     redisTemplate.opsForList();//操作list
     redisTemplate.opsForSet();//操作set
     redisTemplate.opsForZSet();//操作有序set
     */
    private RedisTemplate<String, T> redisTemplate;

    public static final String KEY_SET_PREFIX = "_set:";
    public static final String KEY_LIST_PREFIX = "_list:";

    public T get(String key) {
        LOGGER.debug("get key [{}]", key);
        try {
            if (key == null) {
                return null;
            }
            else {
                //取出redis中的值
                return redisTemplate.opsForValue().get(key);
            }
        }
        catch (Throwable t) {
            LOGGER.error("get key [{}] exception!", key, t);
            throw new CacheException(t);
        }

    }

    public T set(String key, T value) {
        LOGGER.debug("set key [{}]", key);
        try {
            //将结果以键值对的形式放入redis中
            redisTemplate.opsForValue().set(key, value);
            return value;
        }
        catch (Throwable t) {
            LOGGER.error("set key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    public T set(String key, T value, long timeout) {
        LOGGER.debug("set key [{}]", key);
        try {
            //设置redis的值和失效时间
            /**
             TimeUnit:
             TimeUnit.DAYS          //天
             TimeUnit.HOURS         //小时
             TimeUnit.MINUTES       //分钟
             TimeUnit.SECONDS       //秒
             TimeUnit.MILLISECONDS  //毫秒
             */
            redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MINUTES);
            return value;
        }
        catch (Throwable t) {
            LOGGER.error("set key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    public void delete(String key) {
        LOGGER.debug("delete key [{}]", key);
        try {
            //删除这个key和对应的值
            redisTemplate.delete(key);
        }
        catch (Throwable t) {
            LOGGER.error("delete key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    @SuppressWarnings("unchecked")
    public void setSet(String k, T value, long time) {
        String key = KEY_SET_PREFIX + k;
        LOGGER.debug("setSet key [{}]", key);
        try {
            //opsForSet()->redis操作Set类型，然后是返回操作对象（ctrl+2-》L）
            SetOperations<String, T> valueOps = redisTemplate.opsForSet();
            //无序集合中添加元素
            valueOps.add(key, value);
            if (time > 0)
                //设置缓存的过期时间
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        catch (Throwable t) {
            LOGGER.error("setSet key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    public void setSet(String k, T value) {
        setSet(k, value, -1);
    }

    @SuppressWarnings("unchecked")
    public void setSet(String k, Set<T> v, long time) {
        String key = KEY_SET_PREFIX + k;
        LOGGER.debug("setSet key [{}]", key);
        try {
            SetOperations<String, T> setOps = redisTemplate.opsForSet();
            //往redis里放入一个集合
            setOps.add(key, (T[]) v.toArray());
            if (time > 0)
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        catch (Throwable t) {
            LOGGER.error("setSet key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    public void setSet(String k, Set<T> v) {
        setSet(k, v, -1);
    }

    public Set<T> getSet(String k) {
        String key = KEY_SET_PREFIX + k;
        LOGGER.debug("getSet key [{}]", key);
        try {
            SetOperations<String, T> setOps = redisTemplate.opsForSet();
            //返回集合中所有成员
            return setOps.members(key);
        }
        catch (Throwable t) {
            LOGGER.error("getSet key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    public void setList(String k, T v, long time) {
        String key = KEY_LIST_PREFIX + k;
        LOGGER.debug("setList key [{}]", key);
        try {
            ListOperations<String, T> listOps = redisTemplate.opsForList();
            //将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从右边插入）
            listOps.rightPush(key, v);
            if (time > 0)
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        catch (Throwable t) {
            LOGGER.error("setList key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    public void setList(String k, List<T> v, long time) {
        String key = KEY_LIST_PREFIX + k;
        LOGGER.debug("setList key [{}]", key);
        try {
            ListOperations<String, T> listOps = redisTemplate.opsForList();
            //插入一个集合
            listOps.rightPushAll(key, v);
            if (time > 0)
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        catch (Throwable t) {
            LOGGER.error("setList key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    public void setList(String k, List<T> v) {
        setList(k, v, -1);
    }

    public List<T> getList(String k, long start, long end) {
        String key = KEY_LIST_PREFIX + k;
        LOGGER.debug("setList key [{}]", key);
        try {
            ListOperations<String, T> listOps = redisTemplate.opsForList();
            //返回存储在键中的列表的指定元素。偏移开始和停止是基于零的索引，其中0是列表的第一个元素（列表的头部），1是下一个元素
            return listOps.range(key, start, end);
        }
        catch (Throwable t) {
            LOGGER.error("getList key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    public long getListSize(String k) {
        String key = KEY_LIST_PREFIX + k;
        LOGGER.debug("setList key [{}]", key);
        try {
            ListOperations<String, T> listOps = redisTemplate.opsForList();
            //返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。当key存储的值不是列表时返回错误
            return listOps.size(key);
        }
        catch (Throwable t) {
            LOGGER.error("getListSize key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    public long getListSize(ListOperations<String, String> listOps, String k) {
        String key = KEY_LIST_PREFIX + k;
        LOGGER.debug("getListSize key [{}]", key);
        try {
            return listOps.size(key);
        }
        catch (Throwable t) {
            LOGGER.error("getListSize key [{}] exception!", key, t);
            throw new CacheException(t);
        }
    }

    public void setMap(String key, String mapkey, T mapValue) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        //仅当hashKey不存在时才设置散列hashKey的值
        hashOperations.putIfAbsent(key, mapkey, mapValue);
    }

    public void deleteMap(String key, String mapkey) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, mapkey);
    }

    public T getMap(String key, String mapkey) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, mapkey);
    }

    public List<T> getMapValues(String key) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        //获取整个哈希存储的值根据密钥
        return hashOperations.values(key);
    }

    public void setRedisTemplate(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
