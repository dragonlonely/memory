package com.dragon.memory.sms.controller;


import com.dragon.memory.commons.redis.JedisClientPool;
import com.dragon.memory.utils.msg.IndustrySMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
/**
 * @author Administrator
 * @Date 2019/1/9 15:48
 * @Classname SmsController
 */
@Controller
@RequestMapping("/sms")
public class SmsController {

    private static final Logger logger = Logger.getLogger(SmsController.class);

    @Autowired
    private JedisClientPool jedisClientPool;

    @RequestMapping("/msg")
    public String getPhoneCode(String phone) {
        String phoneCode = IndustrySMS.execute(phone);
        jedisClientPool.set(phone, phoneCode);
        jedisClientPool.expire(phone, 120);
        logger.info("验证码已发送"+phoneCode);
        return phoneCode;
    }

}
