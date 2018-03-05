package com.xj.project.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties 工具类
 *
 * @author xiangjing
 * @date 2018/3/5
 * @company 天极云智
 */
public class PropertyUtil {

    private static Logger log = LoggerFactory.getLogger(PropertyUtil.class);

    private String default_path="/jdbc.properties";

    private  Properties prop = null;

    private  Properties readProp(String path){
        prop= new Properties();
        InputStream is =PropertyUtil.class.getClassLoader().getResourceAsStream(path);
        try {
            prop.load(is);
            return prop;
        } catch (IOException e) {
            log.error(path+"，文件读取错误",e);
        }
        return null;

    }

    /**
     * 根据文件名和键名获取值
     * @param fileName
     * @param key
     * @return
     */
    public  String getValue(String fileName, String key){
        Properties prop = readProp(fileName);
        if(prop != null){
            return prop.getProperty(key);
        }
        return null;
    }

    /**
     * 根据键名获取值
     * @param prop
     * @param key
     * @return
     */
    public  String readKeyValue(Properties prop, String key){
        if(prop != null){
            return prop.getProperty(key);
        }
        return null;
    }

    public String getValue(String key){
        Properties prop = readProp(default_path);
        if(prop != null){
            return prop.getProperty(key);
        }
        return null;
    }

}
