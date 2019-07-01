package com.dragon.memory.picture.controller;

import com.dragon.memory.commons.annotation.LogAnnotation;
import com.dragon.memory.picture.pojo.Memory;
import com.dragon.memory.picture.service.MemoryService;
import com.dragon.memory.utils.TextUtils;
import com.dragon.memory.vo.PageBean;
import com.dragon.memory.vo.ShowPicturesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/memory")
public class MemoryController {
    private static final Logger logger=Logger.getLogger(MemoryController.class);

    @Autowired
    public MemoryService memoryService;

    /**
     * 获取全部照片信息
     * @param pageNum
     * @param pageSize
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/getallpictures")
    @LogAnnotation(opertionName = "获取所有照片信息",operationType = "getallpictures")
    public String getAllPictures(Integer pageNum, Integer pageSize, Model model,HttpServletRequest request){
        Object object = request.getSession().getAttribute("user");
        if (object == null) {
            //没有登录，返回登录页面，重新登录
            return "redirect:/login.jsp";
        }
        try {
            pageNum = pageNum == null ? 1 : pageNum;
            pageSize = pageSize == null ? 12 : pageSize;
            PageBean pageBean = memoryService.getAllPictures(pageNum, pageSize);
            model.addAttribute("pageBean", pageBean);
            logger.trace("获取所有图片"+pageBean.toString());
            return "/picturesList";
        } catch (Exception e) {
            e.printStackTrace();
            return "/message";
        }
    }

    /**
     * 查看照片详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getpicturesdetailbyid")
    @LogAnnotation(opertionName = "获取照片详情信息",operationType = "getpicturesdetailbyid")
    public String getGoodsDetailById(Integer id, Model model) {
        try {
            Memory memory=memoryService.getPicturesDetailById(id);
            ShowPicturesVo showPicturesVo=new ShowPicturesVo();
            showPicturesVo.setId(memory.getId());
            showPicturesVo.setPname(memory.getPname());
            showPicturesVo.setPicture(memory.getPicture());
            showPicturesVo.setRemark(memory.getRemark());
            showPicturesVo.setStar(memory.getStar());

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String cDate = sdf.format(memory.getCreatetime());
            showPicturesVo.setCreatetime(cDate);
            model.addAttribute("pictures", showPicturesVo);
            logger.info("获取商品详情"+showPicturesVo.toString());
            return "/picturesDetail";
        } catch (Exception e) {
            e.printStackTrace();
            return "/message";
        }
    }

    /**
     * 模糊查询--通过照片名称查询照片
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/searchpictures")
    @LogAnnotation(opertionName = "查询照片信息",operationType = "searchpictures")
    public String searchPictures(HttpServletRequest request, Model model)throws Exception{
        try {
            request.setCharacterEncoding("UTF-8");
            String picturesname = request.getParameter("picturesname");
            if(TextUtils.empty(picturesname)){
                return "/picturessearch";
            }
            List<Memory> list=memoryService.searchByName(picturesname);
            List<ShowPicturesVo> showPicturesVos=new ArrayList<ShowPicturesVo>();
            for (Memory memory : list) {
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
            model.addAttribute("list",showPicturesVos);
            logger.info("查询图片"+list.toString());
            return "/picturessearch";
        } catch (Exception e) {
            e.printStackTrace();
            return "/message";
        }
    }

}
