package pro.sunhao.seckill.dto;

import lombok.Data;

@Data
public class SeckillExecution {

    private boolean isSuccess;
    private long seckillId;
    private String message;

    public SeckillExecution(boolean isSuccess, long seckillId) {
        this.isSuccess = isSuccess;
        this.seckillId = seckillId;
    }

    public SeckillExecution(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

}
