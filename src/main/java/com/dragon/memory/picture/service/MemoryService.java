package com.dragon.memory.picture.service;

import com.dragon.memory.picture.pojo.Memory;
import com.dragon.memory.vo.PageBean;

import java.util.List;

public interface MemoryService {

    void addPictures(Memory memory);

    PageBean getAllPictures(Integer pageNum, Integer pageSize);

    List<Memory> searchByName(String picturesname);

    Memory getPicturesDetailById(Integer id);

    void deletePictureById(Integer id);

}
