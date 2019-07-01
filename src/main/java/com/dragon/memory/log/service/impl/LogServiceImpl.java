package com.dragon.memory.log.service.impl;


import com.dragon.memory.log.pojo.TbLog;
import com.dragon.memory.log.service.LogService;
import com.dragon.memory.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @Date 2019/1/7 19:46
 * @Classname LogServiceImpl
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;


    public void addLog(TbLog tbLog) {
        logMapper.addLog(tbLog);
    }
}
