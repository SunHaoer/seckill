package pro.sunhao.seckill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Exposer {

    private boolean isLegal;
    private long seckillId;
    private String md5Url;
    private Date startTime;
    private Date endTime;
    private Date nowTime;

    public Exposer(boolean isLegal, long seckillId) {
        this.isLegal = isLegal;
        this.seckillId = seckillId;
    }

    public Exposer(boolean isLegal, long seckillId, Date startTime, Date endTime, Date nowTime) {
        this.isLegal = isLegal;
        this.seckillId = seckillId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.nowTime = nowTime;
    }

    public Exposer(boolean isLegal, long seckillId, String md5Url) {
        this.isLegal = isLegal;
        this.seckillId = seckillId;
        this.md5Url = md5Url;
    }

}
