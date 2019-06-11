package pro.sunhao.seckill.service;

import pro.sunhao.seckill.dto.Exposer;
import pro.sunhao.seckill.dto.SeckillExecution;
import pro.sunhao.seckill.pojo.Seckill;
import pro.sunhao.seckill.pojo.SuccessSeckilled;

import java.util.List;

public interface SeckillService {

    Exposer exportSeckillUrl(long id);

    SeckillExecution doSeckill(long id, String md5Url, long userPhone);

    List<Seckill> findSeckillAll();

    List<SuccessSeckilled> findSuccessSeckilledAll();

}
