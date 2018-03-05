package com.xj.project.jdbc;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * jdbc工具类
 *
 * @author xiangjing
 * @date 2018/3/5
 * @company 天极云智
 */
public class JDBCUtil {
    private static Logger log = LoggerFactory.getLogger(JDBCUtil.class);

    private ConnectionPool connectionPool=null;

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    private  Connection conn;
    private  void  getConnectionPool(){//获取连接
        if(null == connectionPool){
            connectionPool= new ConnectionPool();
        }
    }

    private Connection getCon(){
        getConnectionPool();
       return connectionPool.getConn();
    }

    public List<Map<String,Object>> queryList(String sql, List<Object> param,List<String> queryKey) throws SQLException {

        conn=  getCon();
        ps = conn.prepareStatement(sql);

        if(null!=param){
            for(int i=0;i<param.size();i++){
                ps.setObject(i+1,param.get(i));
            }
        }
        rs= ps.executeQuery();
        List<Map<String,Object>> result=handleData(rs,queryKey);
        System.out.println(result.size());
        connectionPool.commitTran();//提交事物
        close();
        connectionPool.close();
        return result;

    }

    public List<Map<String,Object>> queryList(String sql,List<String> queryKey) throws SQLException {

        conn=  getCon();
        ps = conn.prepareStatement(sql);
        rs= ps.executeQuery();
        List<Map<String,Object>> result=handleData(rs,queryKey);
        System.out.println(result.size());
        connectionPool.commitTran();//提交事物
        close();
        connectionPool.close();
        return result;

    }

    /**
     *
     * @param sql
     * @param param 参数
     * @param queryKey 返回列
     * @return
     * @throws SQLException
     */
    public List<Map<String,Object>> queryList(String sql, List<Object> param,Object queryKey) throws SQLException {

        conn=  getCon();
        ps = conn.prepareStatement(sql);

        if(null!=param){
            for(int i=0;i<param.size();i++){
                ps.setObject(i+1,param.get(i));
            }
        }
        rs= ps.executeQuery();
        List<Map<String,Object>> result=handleData(rs,queryKey);
        System.out.println(result.size());
        connectionPool.commitTran();//提交事物
        close();
        connectionPool.close();
        return result;

    }

    /**
     *
     * @param sql
     * @param param 参数
     * @return 返回所有列
     * @throws SQLException
     */
    public List<Map<String,Object>> queryListByAll(String sql, List<Object> param) throws SQLException {

        conn=  getCon();
        ps = conn.prepareStatement(sql);

        if(null!=param){
            for(int i=0;i<param.size();i++){
                ps.setObject(i+1,param.get(i));
            }
        }
        rs= ps.executeQuery();
        List<Map<String,Object>> result=handleData(rs);
        System.out.println(result.size());
        connectionPool.commitTran();//提交事物
        close();
        connectionPool.close();
        return result;

    }


    private List<Map<String,Object>> handleData(ResultSet rs,List<String> queryKey) throws SQLException {

        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> map =null;
        while (rs.next()){
            map =new HashMap();
            for(String key:queryKey){
                map.put(key,rs.getObject(key));
            }
            data.add(map);
        }
        return data;
    }

    private List<Map<String,Object>> handleData(ResultSet rs) throws SQLException {

        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> map =null;
        ResultSetMetaData rsmd=null;
        while (rs.next()) {
            rsmd= rs.getMetaData();
            map = new HashMap();
            int count = rsmd.getColumnCount();
            String[] name = new String[count];
            for (int i = 0; i < count; i++){
                map.put(rsmd.getColumnName(i + 1),rs.getObject(i+1));

             }
            data.add(map);
        }
        return data;
    }

    private List<Map<String,Object>> handleData(ResultSet rs,Object queryKey) throws SQLException {
        Field[] fields=queryKey.getClass().getDeclaredFields();

        List<String> data = new ArrayList<>();
        for(Field field:fields){
            data.add(field.getName());
        }
        return handleData(rs,data);
    }

    private void close(){//释放资源
        try {
            ps.close();
        } catch (SQLException e) {
            log.error("PreparedStatement close fail",e);
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                log.error("ResultSet close fail", e);
            }
        }
        try {
            rs.close();
        } catch (SQLException e) {
            log.error("ResultSet close fail", e);
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("ResultSet close fail", e);
            }
        }
    }

}
