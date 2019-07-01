package com.dragon.memory.secret.service.impl;

import com.dragon.memory.mapper.SecretMapper;
import com.dragon.memory.secret.pojo.Secret;
import com.dragon.memory.secret.service.SecretService;
import com.dragon.memory.vo.PageBean;
import com.dragon.memory.vo.ShowPicturesVo;
import com.dragon.memory.vo.ShowSecretPicturesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class SecretServiceImpl implements SecretService {

    @Autowired
    private SecretMapper secretMapper;

    public void addSecretPicture(Secret secret) {
        secretMapper.addSecretPicture(secret);
    }

    public PageBean getAllsecretPictures(Integer pageNum, Integer pageSize) {

        List<Secret> secretList=secretMapper.getAllsecretPictures(pageNum,pageSize);
        List<ShowSecretPicturesVo> showSecretPicturesVos=new ArrayList<ShowSecretPicturesVo>();
        for (Secret secret : secretList) {
            ShowSecretPicturesVo showSecretPicturesVo=new ShowSecretPicturesVo();
            showSecretPicturesVo.setId(secret.getId());
            showSecretPicturesVo.setSecretname(secret.getSecretname());
            showSecretPicturesVo.setSecretpicture(secret.getSecretpicture());
            showSecretPicturesVo.setStar(secret.getStar());
            showSecretPicturesVo.setRemark(secret.getRemark());

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String cDate = sdf.format(secret.getCreatedate());
            showSecretPicturesVo.setCreatedate(cDate);
            showSecretPicturesVos.add(showSecretPicturesVo);
        }
        long totalCount=secretMapper.getCount();
        PageBean pageBean = new PageBean(pageNum,pageSize,totalCount);
        pageBean.setData(showSecretPicturesVos);
        return pageBean;
    }

}
