package com.xj.project.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log4j 日志测试
 *
 * @author xiangjing
 * @date 2018/1/18
 * @company 天极云智
 */
public class Log4jTwoTest1 {
    private static  final Logger logger = LoggerFactory.getLogger(Log4jTwoTest1.class);

    public static void main(String[] args) {
        System.out.println(System.getProperty("catalina.base"));
        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");

    }
}
