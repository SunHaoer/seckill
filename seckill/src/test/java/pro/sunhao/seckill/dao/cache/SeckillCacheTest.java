package pro.sunhao.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import pro.sunhao.seckill.Utils.RedisUtil;
import pro.sunhao.seckill.dao.SeckillDao;
import pro.sunhao.seckill.pojo.Seckill;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillCacheTest {

    @Autowired
    private SeckillCache seckillCache;
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void getSeckill() {
        Seckill seckill = seckillCache.getSeckill(1001);
        System.out.println(seckill);
    }

    @Test
    public void putSeckill() {
        Seckill seckill = seckillDao.findBySeckillId(1001);
        System.out.println(seckill);
        seckillCache.putSeckill(seckill);
    }

    @Test
    public void deleteSeckill() {
        seckillCache.deleteSeckill(1001);
    }

    @Test
    public void redisGet() {
        String result = (String)redisUtil.get("隼耗");
        System.out.println(result);
    }

    @Test
    public void test() {
        System.out.println((Seckill)null);
    }

}