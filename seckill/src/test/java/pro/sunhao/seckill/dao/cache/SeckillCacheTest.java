package pro.sunhao.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pro.sunhao.seckill.dao.SeckillDao;
import pro.sunhao.seckill.pojo.Seckill;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillCacheTest {

    @Autowired
    private SeckillCache seckillCache;
    @Autowired
    private SeckillDao seckillDao;

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

}