package com.xj.project.log4j2;

import com.xj.project.Callable.ThreadTest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * log4j2测试
 *
 * @author xiangjing
 * @date 2018/1/17
 * @company 天极云智
 */
public class Log4jTwoTest {

    protected String test;

    Logger logger = LogManager.getLogger(Log4jTwoTest.class.getName());

   /* public static void main(String[] args) {


        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        logger.fatal("fatal level");


    }*/
}
