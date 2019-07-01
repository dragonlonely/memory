package com.dragon.memory.picture.service.impl;

import com.dragon.memory.mapper.MemoryMapper;
import com.dragon.memory.picture.pojo.Memory;
import com.dragon.memory.picture.service.MemoryService;
import com.dragon.memory.vo.PageBean;
import com.dragon.memory.vo.ShowPicturesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryServiceImpl implements MemoryService {

    @Autowired
    private MemoryMapper memoryMapper;

    public void addPictures(Memory memory) {
        memoryMapper.addPictures(memory);
    }

    public PageBean getAllPictures(Integer pageNum, Integer pageSize) {
        List<Memory> picturesList = memoryMapper.getAllPictures(pageNum,pageSize);
        List<ShowPicturesVo> showPicturesVos=new ArrayList<ShowPicturesVo>();
        for (Memory memory : picturesList) {
            ShowPicturesVo showPicturesVo=new ShowPicturesVo();
            showPicturesVo.setId(memory.getId());
            showPicturesVo.setPname(memory.getPname());
            showPicturesVo.setPicture(memory.getPicture());
            showPicturesVo.setRemark(memory.getRemark());
            showPicturesVo.setStar(memory.getStar());

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String cDate = sdf.format(memory.getCreatetime());
            showPicturesVo.setCreatetime(cDate);
            showPicturesVos.add(showPicturesVo);
        }
        long totalCount=memoryMapper.getCount();
        PageBean pageBean = new PageBean(pageNum,pageSize,totalCount);
        pageBean.setData(showPicturesVos);
        return pageBean;
    }

    public List<Memory> searchByName(String picturesname) {
        return memoryMapper.findByName(picturesname);
    }

    public Memory getPicturesDetailById(Integer id) {
        return memoryMapper.getPicturesDetailById(id);
    }

    public void deletePictureById(Integer id) {
        memoryMapper.deletePictureById(id);
    }
}
