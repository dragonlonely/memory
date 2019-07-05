package com.dragon.memory.admin.controller;

import com.dragon.memory.picture.controller.MemoryController;
import com.dragon.memory.picture.pojo.Memory;
import com.dragon.memory.picture.service.MemoryService;
import com.dragon.memory.utils.FastDfsUtils;
import com.dragon.memory.vo.PageBean;
import com.dragon.memory.vo.ShowPicturesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/picture")
public class ShowPicturesController {
    private static final Logger logger=Logger.getLogger(MemoryController.class);

    @Autowired
    private MemoryService memoryService;

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping("/showallpictures")
    public String showAllPictures(HttpServletRequest request){
        try {
           List<Memory> memoryList= memoryService.showAllPictures();
           List<ShowPicturesVo> showPicturesVos=new ArrayList<ShowPicturesVo>();
           for (Memory memory : memoryList) {
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
            request.getSession().setAttribute("pictureList", showPicturesVos);
            //model.addAttribute("pictureList", showPicturesVos);
            logger.trace("获取所有图片"+showPicturesVos.toString());
            return "redirect:/admin/picturesDetails.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "/message";
        }
    }

    @PostMapping("/updatepictures")
    public String updatePictures(HttpServletRequest request, MultipartFile file){
        String id = request.getParameter("id");
        String pname = request.getParameter("pname");
        String star = request.getParameter("star");
        String remark = request.getParameter("remark");

        //更新图片信息
        Memory memory=new Memory();
        memory.setId(Integer.parseInt(id));
        memory.setPname(pname);
        memory.setCreatetime(new Date());
        memory.setStar(Integer.parseInt(star));
        memory.setRemark(remark);

        String picture=null;
        try {
            FastDfsUtils fastDfsUtils = new FastDfsUtils("classpath:conf.properties");
            String filename = file.getOriginalFilename();
            String ext_name = filename.substring(filename.lastIndexOf(".") + 1);
            String path = fastDfsUtils.fileUpload(file.getBytes(), ext_name);
            picture = IMAGE_SERVER_URL + path;
            memory.setPicture(picture);
        } catch (Exception e) {
            e.printStackTrace();
        }
        memoryService.updatePictures(memory);
        logger.info("修改商品==>"+memory.toString());
        return "forward:getgoodslist.action";
    }

    @RequestMapping("/deletepictures")
    public String deletePictures(Integer id){
        try {
            memoryService.deletePictureById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除图片失败");
        }
        logger.info("删除图片成功");
        return "forward:showallpictures.action";
    }

    @RequestMapping("/querypictureslist")
    public String queryPicturesList(HttpServletRequest request) throws Exception{
        List<ShowPicturesVo> showPicturesVos= null;
        try {
            request.setCharacterEncoding("utf-8");
            String pname = request.getParameter("pname");
            String createtime = request.getParameter("createtime");
            List<Memory> memoryList = memoryService.queryPictures(pname, createtime);
            showPicturesVos = new ArrayList<ShowPicturesVo>();
            for (Memory memory : memoryList) {
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.info("查询图片失败!===>"+e.getMessage());
        }
        request.getSession().setAttribute("pictureList", showPicturesVos);
        logger.info("查询图片成功!===>"+showPicturesVos.toString());
        return "redirect:/admin/picturesDetails.jsp";
    }

}
