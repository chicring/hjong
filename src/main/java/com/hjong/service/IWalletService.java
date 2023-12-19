package com.hjong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjong.entity.Wallet;

import java.util.List;

public interface IWalletService extends IService<Wallet> {
    Integer updateWallet();
    Integer findByUserId();
    List<Wallet> findListByAny();
}
