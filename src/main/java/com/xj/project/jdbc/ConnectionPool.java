package com.xj.project.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接池
 *
 * @author xiangjing
 * @date 2018/3/5
 * @company 天极云智
 */
public class ConnectionPool {

    private static Logger log = LoggerFactory.getLogger(PropertyUtil.class);

    private static DruidDataSource dataSource  = new DruidDataSource();
    //声明线程共享变量
    public static ThreadLocal<Connection> container = new ThreadLocal<Connection>();
    //这一步可直接使用spring 的配置形式加载
    static {
        dataSource.setUrl("jdbc:mysql://localhost:3306/mytest?useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("root");//用户名
        dataSource.setPassword("root");//密码
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
    }

    public Connection getConn(){
        Connection conn=null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            container.set(conn);
        } catch (SQLException e) {
            log.error("connet get failed,"+e);
        }
        return conn;
    }


    public void close(){
        Connection conn=container.get();
        if(null == conn){
            log.error("connect not exits ,cant't close");
            return ;
        }else{
            try {
                conn.close();
                container.remove();//如果连接关闭了，则移除这个连接
            } catch (SQLException e) {
                log.error("connect close fiail,", e);
            }
        }
    }

    /**
     * 提交事物
     */
    public void commitTran(){
        Connection conn=container.get();
        if(null == conn){
            log.error("connect not exits ,cant't commit Transaction");
            return ;
        }else{
            try {
                conn.commit();
            } catch (SQLException e) {
                log.error("can not commit Transaction,", e);
            }
        }
    }

    /**
     * 事物回滚
     */
    /***回滚事务*/
    public static void rollback(){
        try{
            Connection conn=container.get();//检查当前线程是否存在连接
            if(conn!=null){
                conn.rollback();//回滚事务
            }
        }catch(Exception e){
            log.error("can not rollback this  Transaction,", e);
        }
    }
}
