package com.xj.project.jdbc;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * ces
 *
 * @author xiangjing
 * @date 2018/3/5
 * @company 天极云智
 */
public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        String sql="select * from course limit ?,?";
        JDBCUtil jdbc =new JDBCUtil();
        System.out.println(jdbc.queryListByAll(sql, Arrays.asList(0, 5)));;
    }
}
