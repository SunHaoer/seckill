package pro.sunhao.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillDaoTest {

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void findAll() {
        System.out.println(seckillDao.findAll());
    }

    @Test
    public void findBySeckillId() {
        System.out.println(seckillDao.findBySeckillId(1000));
    }

    @Test
    public void reduceNumber() {
        System.out.println(seckillDao.reduceNumber(1000, new Date()));
    }

}
