package pro.sunhao.seckill.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sunhao.seckill.pojo.SuccessSeckilled;

public interface SuccessSeckilledDao extends JpaRepository<SuccessSeckilled, Long> {

    SuccessSeckilled findBySeckillId(long seckillId);

    SuccessSeckilled findBySeckillIdAndUserPhone(long seckillId, long phone);

}
