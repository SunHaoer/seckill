package pro.sunhao.seckill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import pro.sunhao.seckill.dao.SeckillDao;
import pro.sunhao.seckill.dao.SuccessSeckilledDao;
import pro.sunhao.seckill.dao.cache.SeckillCache;
import pro.sunhao.seckill.dto.Exposer;
import pro.sunhao.seckill.dto.SeckillExecution;
import pro.sunhao.seckill.exception.RepeatKillException;
import pro.sunhao.seckill.exception.SeckillCloseException;
import pro.sunhao.seckill.exception.SeckillException;
import pro.sunhao.seckill.pojo.Seckill;
import pro.sunhao.seckill.pojo.SuccessSeckilled;
import pro.sunhao.seckill.service.SeckillService;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    final private String SALT = "sunhao";

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessSeckilledDao successSeckilledDao;

    @Autowired
    private SeckillCache seckillCache;

    @Override
    public Exposer exportSeckillUrl(long id) {
        // 缓存优化
        Seckill seckill = seckillCache.getSeckill(id);
        if(seckill == null) {
            seckill = seckillDao.findBySeckillId(id);
            if(seckill == null) {    // id错误
                return new Exposer(false, id);
            } else {
                seckillCache.putSeckill(seckill);
            }
        }
        Date nowTime = new Date();
        if(nowTime.getTime() < seckill.getStartTime().getTime() || nowTime.getTime() > seckill.getEndTime().getTime()) {    // 不在时间范围内
            return new Exposer(false, id, seckill.getStartTime(), seckill.getEndTime(), nowTime);
        }
        String md5Url = getMD5Url(id);
        return new Exposer(true, id ,md5Url);
    }

    @Transactional
    @Override
    public SeckillExecution doSeckill(long id, String md5Url, long userPhone) {
        try {
            if(md5Url == null || !md5Url.equals(getMD5Url(id))) {
                throw new SeckillException("url and id match");
            }
            SuccessSeckilled successSeckilled = successSeckilledDao.findBySeckillIdAndUserPhone(id, userPhone);
            if(successSeckilled != null) {
                throw new RepeatKillException("repeat seckill");
            }
            Date nowTime = new Date();
            if(seckillDao.reduceNumber(id, nowTime) == 0) {    // 减库存
                throw new SeckillCloseException("seckill finished");
            }
            successSeckilled = new SuccessSeckilled(id, userPhone, 1, nowTime);
            successSeckilledDao.saveAndFlush(successSeckilled);    // 插入记录
        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return new SeckillExecution(true, id);
    }

    @Override
    public List<Seckill> findSeckillAll() {
        return seckillDao.findAll();
    }

    @Override
    public List<SuccessSeckilled> findSuccessSeckilledAll() {
        return successSeckilledDao.findAll();
    }

    private String getMD5Url(long id) {
        String baseStr = SALT + "/" + id;
        String md5Str = DigestUtils.md5DigestAsHex(baseStr.getBytes());
        return md5Str;
    }

}
