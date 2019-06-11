package pro.sunhao.seckill.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pro.sunhao.seckill.pojo.Seckill;

import java.util.List;

public interface TestDao extends JpaRepository<Seckill, Long> {

    List<Seckill> findAll();

}
