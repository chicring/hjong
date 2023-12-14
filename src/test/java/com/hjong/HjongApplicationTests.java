package com.hjong;

import com.hjong.mapper.SharefilesMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HjongApplicationTests {

    @Resource
    SharefilesMapper sharefilesMapper;

    @Test
    void contextLoads() {

    }

}
