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
//        Page<PostAndUserVO> page = new Page<>(1,20);
//        QueryWrapper<PostAndUserVO> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("type_id",2);
//        System.out.println(userPostsMapper.selectByAny(page,queryWrapper).getRecords());

    }

}
