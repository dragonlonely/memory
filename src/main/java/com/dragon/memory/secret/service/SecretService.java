package com.dragon.memory.secret.service;

import com.dragon.memory.secret.pojo.Secret;
import com.dragon.memory.vo.PageBean;


public interface SecretService {

    void addSecretPicture(Secret secret);

    PageBean getAllsecretPictures(Integer pageNum, Integer pageSize);


}
