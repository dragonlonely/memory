package com.dragon.memory.mapper;

import com.dragon.memory.secret.pojo.Secret;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecretMapper {

    void addSecretPicture(Secret secret);

    List<Secret> getAllsecretPictures(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    long getCount();

}
