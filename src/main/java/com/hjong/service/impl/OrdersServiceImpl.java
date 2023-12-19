package com.hjong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjong.entity.Orders;
import com.hjong.mapper.OrdersMapper;
import com.hjong.service.IOrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
    @Override
    public Integer createOrder() {
        return null;
    }

    @Override
    public Integer updateOrder() {
        return null;
    }

    @Override
    public Integer findListById() {
        return null;
    }

    @Override
    public Integer findOneByUserId() {
        return null;
    }
}
