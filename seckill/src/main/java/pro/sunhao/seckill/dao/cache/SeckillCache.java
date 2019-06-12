package pro.sunhao.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import pro.sunhao.seckill.pojo.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SeckillCache {

    private final String KEY_PREFIX = "seckill:";
    private final int REDIS_TIMEOUT = 60 * 60;
    private final JedisPool jedisPool;
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public SeckillCache(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public Seckill getSeckill(long seckillId) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = getRedisKey(seckillId);
                byte[] bytes = jedis.get(key.getBytes());
                if(bytes != null) {
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = getRedisKey(seckill.getSeckillId());
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate((LinkedBuffer.DEFAULT_BUFFER_SIZE)));
                String result = jedis.setex(key.getBytes(), REDIS_TIMEOUT, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteSeckill(long seckillId) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = getRedisKey(seckillId);
                jedis.del(key);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getRedisKey(long id) {
        return KEY_PREFIX + id;
    }

}
