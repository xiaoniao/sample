package com.liuzhuang.thread.connection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * 模拟数据库连接
 */
public class ConnectionDriver {

  /**
   * 模拟数据库访问延迟
   */
  static class ConnectionHandler implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (method.getName().equals("commit")) {
        TimeUnit.MILLISECONDS.sleep(100);
      }
      return null;
    }
  }

  /**
   * 创建数据库连接
   */
  public static final Connection createConnection() {
    // 动态代理由jvm实现connection实现类
    return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
        new Class<?>[] { Connection.class }, new ConnectionHandler());
  }
}
