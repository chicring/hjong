package com.hjong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("Orders")
public class Orders {

    @TableId(type = IdType.AUTO)
    private Integer orderId;
    private Integer userId;
    private Integer fileId;
    private LocalDateTime orderDate;
    private String orderStatus;  //'订单状态：0：支付中，1: 支付完成，2：异常',
    private String payStatus;   //'支付状态：0：待结算，1: 已结算，2：已退款',
    private Double totalPrice;
    private String paymentMethod;
}
