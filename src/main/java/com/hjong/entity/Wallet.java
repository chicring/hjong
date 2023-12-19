package com.hjong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Wallet")
public class Wallet {
    @TableId(type = IdType.AUTO)
    private Integer walletId;

    private Integer userId;


    private double balance; //余额

    private double pendingAmount; //已结算的

    private double settledAmount; //未结算

    private String walletAddress; //钱包地址，收款

    private String walletStatus;  // 0:正常 1异常
}
