package com.dragon.memory.admin.controller;


import com.dragon.memory.commons.annotation.LogAnnotation;
import com.dragon.memory.picture.pojo.Memory;
import com.dragon.memory.picture.service.MemoryService;
import com.dragon.memory.utils.FastDfsUtils;
import com.dragon.memory.vo.PicturesVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

/**
 * @author Administrator
 * @Date 2019/1/5 16:58
 * @Classname AddGoodsController
 */
@Controller
@RequestMapping("/backadd")
public class AddPicturesController {
    private static final Logger logger=Logger.getLogger(AddPicturesController.class);

    @Autowired
    private MemoryService memoryService;

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    /**
     * 上传照片
     * @param picturesVo
     * @param file
     * @return
     */
    @PostMapping("/addpictures")
    @LogAnnotation(opertionName = "管理员上传照片",operationType = "addpictures")
    public String addPictures(PicturesVo picturesVo, MultipartFile file) {
        String picture = null;
        try {
            FastDfsUtils fastDfsUtils = new FastDfsUtils("classpath:conf.properties");
            String filename = file.getOriginalFilename();
            long fileSize = file.getSize();
            System.out.println("文件大小: "+fileSize);
            if(fileSize > 10485760){
                logger.info("上传的文件大小超出最大值!");
                return "/fileerror";
            }
            String ext_name = filename.substring(filename.lastIndexOf(".") + 1);
            String path = fastDfsUtils.fileUpload(file.getBytes(), ext_name);
            picture = IMAGE_SERVER_URL + path;

            Memory memory = new Memory();
            memory.setPname(picturesVo.getPname());
            memory.setPicture(picture);
            memory.setStar(picturesVo.getStar());
            memory.setCreatetime(new Date());
            memory.setRemark(picturesVo.getRemark());
            memoryService.addPictures(memory);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("添加图片失败!");
        }
        logger.info("添加图片成功!");
        return "/admin/addPictures";
    }

}
