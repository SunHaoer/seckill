package pro.sunhao.seckill.dao.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pro.sunhao.seckill.Utils.RedisUtil;
import pro.sunhao.seckill.pojo.Seckill;

@Repository
public class SeckillCache {

    private final String KEY_PREFIX = "seckill:";
    private final long REDIS_TIMEOUT = 60;

    @Autowired
    private RedisUtil redisUtil;

    public Seckill getSeckill(long seckillId) {
        return (Seckill) redisUtil.get(getRedisKey(seckillId));
    }

    public boolean putSeckill(Seckill seckill) {
        return redisUtil.set(getRedisKey(seckill.getSeckillId()), seckill, REDIS_TIMEOUT);
    }

    public void deleteSeckill(long seckillId) {
        redisUtil.remove(getRedisKey(seckillId));
    }

    private String getRedisKey(long id) {
        return KEY_PREFIX + id;
    }

}
