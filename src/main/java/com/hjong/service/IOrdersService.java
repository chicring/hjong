package com.hjong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjong.entity.Orders;

public interface IOrdersService extends IService<Orders> {
    Integer createOrder();
    Integer updateOrder();
    Integer findListById();
    Integer findOneByUserId();
}
