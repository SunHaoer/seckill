package pro.sunhao.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pro.sunhao.seckill.pojo.SuccessSeckilled;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuccessSeckillDaoTest {

    @Autowired
    private SuccessSeckilledDao successSeckillDao;

    @Test
    public void saveSuccessSeckill() {
        SuccessSeckilled successKilled = new SuccessSeckilled(1000L,15958566990L, 1, new Date());
        System.out.println(successSeckillDao.saveAndFlush(successKilled));
    }

    @Test
    public void findAllBySeckillIdAndUserPhone() {
        System.out.println(successSeckillDao.findBySeckillIdAndUserPhone(1003, 15958566981L));
    }

    @Test
    public void findAll() {
        System.out.println(successSeckillDao.findAll());
    }

}
