package com.hjong;

import com.hjong.mapper.OrdersMapper;
import com.hjong.mapper.PaidFilsMapper;
import com.hjong.service.impl.OrdersServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HjongApplicationTests {

    @Resource
    OrdersServiceImpl IOrdersService;

    @Resource
    OrdersMapper ordersMapper;

    @Resource
    PaidFilsMapper paidFilsMapper;

    @Test
    void contextLoads() {
        System.out.println(IOrdersService.query().list());
    }

}
