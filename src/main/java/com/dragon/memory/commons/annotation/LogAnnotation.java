package com.dragon.memory.commons.annotation;

import java.lang.annotation.*;

/**
 * @Classname LogAnnotation
 * @Description TODO
 * @Date 2019/1/7 19:29
 * @Created by Administrator
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    //别名
    String opertionName() default ""; //操作名称 比如 登录
    String operationType() default ""; //操作类型 比如 login

}
