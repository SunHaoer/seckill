package pro.sunhao.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceTest {

    @Autowired
    SeckillService seckillService;

    @Test
    public void exportSeckillUrl() {
        System.out.println(seckillService.exportSeckillUrl(200));
        System.out.println(seckillService.exportSeckillUrl(1001));
        System.out.println(seckillService.exportSeckillUrl(1003));
    }

    @Test
    public void doSeckill() {
        long id = 1001;
        String md5Url = getMD5Url(id);
        long userPhone = 15958566986L;
        System.out.println(seckillService.doSeckill(id, md5Url, userPhone));
    }

    @Test
    public void getMD5Url() {
        long id = 1002;
        System.out.println(getMD5Url(id));
    }

    private String getMD5Url(long id) {
        String baseStr = "sunhao" + "/" + id;
        String md5Str = DigestUtils.md5DigestAsHex(baseStr.getBytes());
        return md5Str;
    }

}
