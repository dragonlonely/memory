package com.dragon.memory.secret.controller;

import com.dragon.memory.commons.annotation.LogAnnotation;
import com.dragon.memory.picture.pojo.Memory;
import com.dragon.memory.picture.service.MemoryService;
import com.dragon.memory.secret.pojo.Secret;
import com.dragon.memory.secret.service.SecretService;
import com.dragon.memory.user.pojo.User;
import com.dragon.memory.user.service.UserService;
import com.dragon.memory.vo.PageBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/secret")
public class SecretController {
    private static final Logger logger=Logger.getLogger(SecretController.class);

    @Autowired
    private SecretService secretService;

    @Autowired
    private MemoryService memoryService;

    @Autowired
    private UserService userService;

    /**
     * 获取私密相册
     * @param pageNum
     * @param pageSize
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/getallsecretpictures")
    @LogAnnotation(opertionName = "获取所有私密相册信息",operationType = "getallsecretpictures")
    public String getAllSecretPictures(Integer pageNum, Integer pageSize, Model model, HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        int role=userService.getRoleByUsername(username);
        if (username == null || role==1) {
            //没有登录，返回登录页面，重新登录
            return "/secret";
        }
        try {
            pageNum = pageNum == null ? 1 : pageNum;
            pageSize = pageSize == null ? 12 : pageSize;
            PageBean pageBean = secretService.getAllsecretPictures(pageNum, pageSize);
            model.addAttribute("pageBean", pageBean);
            logger.trace("获取所有私密图片"+pageBean.toString());
            return "/secretPicturesList";
        } catch (Exception e) {
            e.printStackTrace();
            return "/message";
        }
    }

    /**
     * 添加照片到私密相册
     * @param id 照片id
     * @return
     */
    @RequestMapping("/addsecretpicture")
    @LogAnnotation(opertionName = "加入私密相册",operationType = "addsecretpicture")
    public String addSecretPicture(Integer id){
        try {
            Memory memory=memoryService.getPicturesDetailById(id);
            Secret secret=new Secret();
            secret.setSecretname(memory.getPname());
            secret.setSecretpicture(memory.getPicture());
            secret.setRemark(memory.getRemark());
            secret.setStar(memory.getStar());
            secret.setCreatedate(new Date());
            secretService.addSecretPicture(secret);
            logger.trace("加入私密相册成功!");
            //加入私密相册后，删除公共列表中的对应的相片
            memoryService.deletePictureById(id);
            logger.trace("删除对象照片成功!");
            return "forward:getallsecretpictures.action";
        } catch (Exception e) {
            e.printStackTrace();
            return "/message";
        }

    }

}
