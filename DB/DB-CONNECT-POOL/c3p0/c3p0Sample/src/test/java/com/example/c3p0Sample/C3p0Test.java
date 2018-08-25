package com.example.c3p0Sample;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * minPoolSize(5)
 *
 * acquireIncrement(5)
 *
 * maxPoolSize(20)
 *
 * checkoutTimeout(1000)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = C3p0SampleApplication.class)
public class C3p0Test {


    @Autowired
    private ComboPooledDataSource comboPooledDataSource;

    /**
     * 获取能获取的最大连接
     * 获取连接后不关闭
     */
    @Test
    public void testGetConnection() throws SQLException {
        for (int i = 0; i < 30; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            System.out.println("getConnection " + i);
        }
    }

    /**
     * 单线程
     */
    @Test
    public void testExecute() throws SQLException {
        for (int i = 0; i < 3000; i++) {
            try (
                Connection connection = comboPooledDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM op_activity");
                ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                }
                System.out.println("connections : " + comboPooledDataSource.getNumConnections() +
                        " forAllUsers:" + comboPooledDataSource.getNumConnectionsAllUsers() +
                        " Idle:" + comboPooledDataSource.getNumIdleConnections() +
                        " busy:" + comboPooledDataSource.getNumBusyConnections());
            }
        }
    }

    /**
     * 多线程
     * java.sql.SQLException: An attempt by a client to checkout a Connection has timed out.
     */
    @Test
    public void testMultiThreadExecute() {
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try (
                        Connection connection = comboPooledDataSource.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM op_activity");
                        ResultSet resultSet = preparedStatement.executeQuery();
                ) {
                    System.out.println(countDownLatch.getCount() + " ." +
                            " connections:" + comboPooledDataSource.getNumConnections() +
                            " forAllUsers:" + comboPooledDataSource.getNumConnectionsAllUsers() +
                            " Idle:" + comboPooledDataSource.getNumIdleConnections() +
                            " busy:" + comboPooledDataSource.getNumBusyConnections());

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

    /**
     * 和 getConnection 区别？
     */
    // Connection connection = comboPooledDataSource.getConnectionPoolDataSource().getPooledConnection().getConnection();
}
