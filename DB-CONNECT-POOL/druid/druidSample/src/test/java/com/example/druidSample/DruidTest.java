package com.example.druidSample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liuzhuang on 5/2/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DruidSampleApplication.class)
public class DruidTest {

    @Autowired
    private DataSource dataSource;

    /**
     * 获取多个连接，超出 max_active 后会阻塞等待，而并不是报错
     */
    @Test
    public void testGetConnection() throws SQLException {
        for (int i = 1; i < 1000; i++) {
            Connection connection = dataSource.getConnection();
            System.out.println(i + " : " + connection);
        }
    }

    /**
     * 单线程
     */
    @Test
    public void testExecute() throws SQLException {
        for (int i = 0; i < 1000; i++) {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM fud_fund_product limit 1");
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                }
            }
        }
    }

    /**
     * 多线程
     *
     * druid 支持多线程访问
     */
    @Test
    public void testMultiThreadExecute() {
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try (
                        Connection connection = dataSource.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_order_0");
                        ResultSet resultSet = preparedStatement.executeQuery();
                ) {
                    resultSet.next();
                    System.out.println(countDownLatch.getCount() + " " + resultSet.getString(1));

                } catch (SQLException e) {
                    e.printStackTrace();

                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
            System.out.println("查询执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
