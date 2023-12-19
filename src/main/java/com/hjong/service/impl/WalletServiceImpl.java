package com.hjong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjong.entity.Wallet;
import com.hjong.mapper.WalletMapper;
import com.hjong.service.IWalletService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements IWalletService {
    @Override
    public Integer updateWallet() {
        return null;
    }

    @Override
    public Integer findByUserId() {
        return null;
    }

    @Override
    public List<Wallet> findListByAny() {
        return null;
    }
}
