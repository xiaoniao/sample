package com.liuzhuang.projecta;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Hello world!
 * 动态代理
 */
public class App {
  public static void main(String[] args) {
    // com.liuzhuang.projectb.App.main(null);
    // com.liuzhuang.projectd.App.main(null);
    // com.liuzhuang.projectx.App.main(null);

    // Proxy proxy = new Proxy();
    // proxy.commit();

    // 真正的实现类
    Subject realSubject = new RealSubject();
    
    // 以下的操作为生成Proxy结构的类,通过底层的jvm来实现。并在方法调用的时候执行InvocationHandler.invoke方法,
    // 而我们在invoke方法中真正调用真正实现类的方法,核心是我们把代理的代码放入到InvocationHandler中
    ClassLoader classLoader = Subject.class.getClassLoader();
    Class<?>[] interfaces = { Subject.class, BrotherSubject.class}; // 如果实现类实现了多个接口，可以执行多个接口
    InvocationHandler invocationHandler = new MyInvocationHandler(realSubject);
    
    Subject subjectProxy = (Subject) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    subjectProxy.init();
    subjectProxy.commit();
    
    BrotherSubject brotherproxy = (BrotherSubject) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    brotherproxy.brother();
  }

  // 核心
  private static class MyInvocationHandler implements InvocationHandler {
    
    // 真正的实现类
    private Subject subject;
    
    public MyInvocationHandler(Subject subject) {
      this.subject = subject;
    }
    
    /**
     * @param proxy 代理类
     * @param method 所执行方法
     * @param args 执行方法所需参数
     * @return 方法返回结果
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      Object result = null;
      System.out.println("call before");
      result = method.invoke(subject, args); // 反射调用真正的实现类 subject 的对应方法
      System.out.println("call after");
      return result;
    }
  }
}
