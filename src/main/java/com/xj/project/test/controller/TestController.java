package com.xj.project.test.controller;

import com.xj.project.utils.ProPertyUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ces
 *
 * @author xiangjing
 * @date 2018/1/18
 * @company 天极云智
 */
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("test")
    public String test(){
        logger.info("开始测试日志,"+ System.getProperty("sys:catalina.base")+","+System.getProperty("sys:catalina.home"));
        return "ces";
    }
}
