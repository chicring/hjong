package com.hjong.service.impl;

import com.hjong.entity.Logs;
import com.hjong.mapper.LogsMapper;
import com.hjong.service.ILogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs> implements ILogsService {

}
