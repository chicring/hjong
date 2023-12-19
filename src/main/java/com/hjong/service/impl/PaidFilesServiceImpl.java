package com.hjong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjong.entity.PaidFiles;
import com.hjong.mapper.PaidFilsMapper;
import com.hjong.service.IPaidFilesService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class PaidFilesServiceImpl extends ServiceImpl<PaidFilsMapper, PaidFiles> implements IPaidFilesService {
    @Override
    public PaidFiles findOneById() {

        return null;
    }

    @Override
    public List<PaidFiles> findListByUserId() {

        return null;
    }

    @Override
    public List<PaidFiles> findListByAny() {
        return null;
    }

    @Override
    public boolean createPaidFiles(PaidFiles paidFiles) {
        paidFiles.setFileLink(generateLink());
        return save(paidFiles);
    }

    @Override
    public Integer updatePaidFiles() {
        return null;
    }

    @Override
    public Integer detectPaidFiles() {
        return null;
    }

    private String generateLink(){

        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final SecureRandom random = new SecureRandom();
        int length = 11;

        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}
