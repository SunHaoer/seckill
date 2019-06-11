package pro.sunhao.seckill.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pro.sunhao.seckill.pojo.Seckill;

import java.util.Date;
import java.util.List;

public interface SeckillDao extends JpaRepository<Seckill, Long> {

    /**
     * 根据id查秒杀对象
     * @param id
     * @return
     */
    Seckill findBySeckillId(long id);

    /**
     * 减库存
     * @param seckillId
     * @param nowTime
     * @return
     */
    @Transactional
    @Modifying
    @Query("update Seckill seckill set seckill.number = seckill.number - 1 where seckill.seckillId = ?1 and seckill.number > 0 and seckill.startTime <= ?2 and seckill.endTime >= ?2")
    Integer reduceNumber(long seckillId, Date nowTime);

}
