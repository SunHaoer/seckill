package pro.sunhao.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sunhao.seckill.dto.Exposer;
import pro.sunhao.seckill.dto.SeckillExecution;
import pro.sunhao.seckill.pojo.Seckill;
import pro.sunhao.seckill.pojo.SuccessSeckilled;
import pro.sunhao.seckill.service.SeckillService;

import java.util.Date;
import java.util.List;

@RestController
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @RequestMapping("/getSeckillUrl")
    public Exposer getSeckillUrl(long id) {
        Exposer exposer = new Exposer(false, id);    // 默认失败
        try {
            exposer = seckillService.exportSeckillUrl(id);    // 成功
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exposer;
    }

    @RequestMapping("/doSeckill/{md5Url}/{id}")
    public SeckillExecution doSeckill(@PathVariable long id, @PathVariable String md5Url, long userPhone) {
        SeckillExecution seckillExecution;
        try {
            seckillExecution = seckillService.doSeckill(id, md5Url, userPhone);    // 秒杀成功
        } catch (Exception e) {
            seckillExecution = new SeckillExecution(false, e.getMessage());    // 秒杀失败
        }
        return seckillExecution;
    }

    @RequestMapping("/findSeckillAll")
    public List<Seckill> findSeckillAll() {
        return seckillService.findSeckillAll();
    }

    @RequestMapping("/findSuccessSeckilledAll")
    public List<SuccessSeckilled> findSuccessSeckilledAll() {
        return seckillService.findSuccessSeckilledAll();
    }

}
