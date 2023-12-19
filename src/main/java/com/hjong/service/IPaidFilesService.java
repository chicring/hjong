package com.hjong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjong.entity.PaidFiles;

import java.util.List;

public interface IPaidFilesService extends IService<PaidFiles> {

    PaidFiles findOneById();
    List<PaidFiles> findListByUserId();
    List<PaidFiles> findListByAny();
    boolean createPaidFiles(PaidFiles paidFiles);
    Integer updatePaidFiles();
    Integer detectPaidFiles();
}
