package com.hjong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjong.entity.Sharefiles;
import com.hjong.entity.vo.ShareCreateVo;
import com.hjong.entity.vo.ShareFileVO;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
public interface ISharefilesService extends IService<Sharefiles> {

    Sharefiles createShare(ShareCreateVo vo);
    Sharefiles Share(String password,Integer expire_time,Integer file_id,Integer user_id);
    Boolean deleteShare(Integer shareId);
    IPage<ShareFileVO> findAllById(Integer userId, Integer current);
    Map<String,Object> findByLink(String link,String password);
    IPage<ShareFileVO> findHotByView();
    Integer updateShareViewsByLink(String link);
    Integer updateDownloadCountsByLink(String link);

}
