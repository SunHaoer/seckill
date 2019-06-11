package pro.sunhao.seckill.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seckill {

    @Id
    private Long seckillId;
    private String name;
    private Integer number;
    private Date startTime;
    private Date endTime;
    private Date createTime;

}
