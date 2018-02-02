package com.xj.project.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 系统配置工具类
 *
 * @author xiangjing
 * @date 2017/9/5
 * @company 天极云智
 */
public class ProPertyUtil {

    private static String peropertiesUrl = "properties/config.properties";

    private static final Logger logger = LoggerFactory.getLogger(ProPertyUtil.class);
    private static Properties props;

    static {
        loadProps();
    }

    private static void loadProps() {
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
            in = ProPertyUtil.class.getClassLoader().getResourceAsStream(peropertiesUrl);
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
        logger.info("properties文件内容：" + props);
    }

    public static String getValue(String key){
       return  props.getProperty(key);
    }

    public static String getValue(String key,String defaultValue){
        return  props.getProperty(key,defaultValue);
    }

}
