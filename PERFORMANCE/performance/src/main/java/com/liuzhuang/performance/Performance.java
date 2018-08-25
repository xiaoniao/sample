package com.liuzhuang.performance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 找出最优的数据库连接数目<br>
 * 测试N秒钟内，N个数据库连接，可以插入多少条数据。<br>
 * 测试N秒钟内，N个数据库连接，可以查询多少条数据。<br>
 * 测试N秒钟内，N个数据库连接，可以更新多少条数据。<br>
 */
public class Performance {

  private static int threadcount = 20;
  private static volatile int execCount = 0;

  public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
    testInsert();
    testQuery();
    testUpdate();
  }

  private static void testInsert() throws InterruptedException {
    System.out.println("==========insert:");
    List<String> result = new ArrayList<>();
    for (int i = 1; i <= threadcount; i++) {
      execCount = 0;
      List<InsertThread> threads = multiConnection(i);
      Thread.sleep(1000);
      for (InsertThread thread : threads) {
        thread.interrupt();
      }
      result.add(execCount + "," + i);
      Thread.sleep(5000);//等待上次测试连接关闭
    }
    printResult(result);
  }

  // 打印排序结果
  private static void printResult(List<String> result) {
    Collections.sort(result, new Comparator<String>() {

      @Override
      public int compare(String o1, String o2) {
        if (Integer.valueOf(o1.split(",")[0]) > Integer.valueOf(o2.split(",")[0])) {
          return -1;
        }
        return 0;
      }
    });
    System.out.println("执行次数,连接数量");
    for (int i = 0; i < result.size(); i++) {
      System.out.println(result.get(i));
    }
  }

  private static void testQuery() throws InterruptedException {
    System.out.println("==========select:");
    List<String> result = new ArrayList<>();
    for (int i = 1; i <= threadcount; i++) {
      execCount = 0;
      List<SelectThread> threads = multiConnectionSelectThread(i);
      Thread.sleep(1000);
      for (SelectThread thread : threads) {
        thread.interrupt();
      }
      result.add(execCount + "," + i);
      Thread.sleep(5000);//等待上次测试连接关闭
    }
    printResult(result);
  }

  private static void testUpdate() throws InterruptedException {
    System.out.println("==========update:");
    List<String> result = new ArrayList<>();
    for (int i = 1; i <= threadcount; i++) {
      execCount = 0;
      List<UpdateThread> threads = multiConnectionUpdateThread(i);
      Thread.sleep(1000);
      for (UpdateThread thread : threads) {
        thread.interrupt();
      }
      result.add(execCount + "," + i);
      Thread.sleep(5000);//等待上次测试连接关闭
    }
    printResult(result);
  }

  private static List<InsertThread> multiConnection(int count) {
    List<InsertThread> threads = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      InsertThread runnable = new InsertThread();
      Thread thread = new Thread(runnable);
      thread.start();
      threads.add(runnable);
    }
    return threads;
  }

  private static List<SelectThread> multiConnectionSelectThread(int count) {
    List<SelectThread> threads = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      SelectThread runnable = new SelectThread();
      Thread thread = new Thread(runnable);
      thread.start();
      threads.add(runnable);
    }
    return threads;
  }

  private static List<UpdateThread> multiConnectionUpdateThread(int count) {
    List<UpdateThread> threads = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      UpdateThread runnable = new UpdateThread();
      Thread thread = new Thread(runnable);
      thread.start();
      threads.add(runnable);
    }
    return threads;
  }

  /**
   * 插入数据线程
   */
  private static class InsertThread implements Runnable {

    private volatile boolean isInterrupt;

    @Override
    public void run() {
      Connection connection = null;
      try {
        // 获取一个数据库连接
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/s1", "root", "b9xcc4z2");
        for (;;) {
          if (isInterrupt) {
            break;
          }
          PreparedStatement pstmt = connection.prepareStatement("insert into article (title, content) values (?, ?)");
          pstmt.setString(1, "title");
          pstmt.setString(2, "content");
          pstmt.execute();
          execCount++;
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (connection != null) {
          try {
            connection.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    }

    public void interrupt() {
      isInterrupt = true;
    }
  }

  /**
   * 查询
   */
  private static class SelectThread implements Runnable {

    private volatile boolean isInterrupt;

    @Override
    public void run() {
      Connection connection = null;
      try {
        // 获取一个数据库连接
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/s1", "root", "b9xcc4z2");
        for (;;) {
          if (isInterrupt) {
            break;
          }
          PreparedStatement pstmt = connection.prepareStatement("select * from article where id = 1");
          pstmt.executeQuery();
          execCount++;
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (connection != null) {
          try {
            connection.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    }

    public void interrupt() {
      isInterrupt = true;
    }
  }

  /**
   * 更新
   */
  private static class UpdateThread implements Runnable {

    private volatile boolean isInterrupt;

    @Override
    public void run() {
      Connection connection = null;
      try {
        // 获取一个数据库连接
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/s1", "root", "b9xcc4z2");
        for (;;) {
          if (isInterrupt) {
            break;
          }
          PreparedStatement pstmt = connection.prepareStatement("update article set title = ? where id = 1");
          pstmt.setString(1, "hello");
          pstmt.execute();
          execCount++;
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (connection != null) {
          try {
            connection.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    }

    public void interrupt() {
      isInterrupt = true;
    }
  }
}