package com.liuzhuang.projectb;

import java.lang.reflect.Method;

/**
 * 程序执行的时候,都会使用类加载器把用到的所有类全部加载进去.我们可以通过debug来查看类的加载顺序.
 * 在ClassLoader的loadClass方法中打上断点,debug执行main方法,单步调试观察每次传入到loadClass的类名和当前类加载器,有了这一步就会对类加载器很清晰了
 */
public class App {
  
  public static void main(String[] args) {
    showClassloader();
    defineDifferentLoader();
  }

  private static void showClassloader() {
    ClassLoader classLoader = App.class.getClassLoader();
    System.out.println("应用程序class加载器:" + classLoader); // 应用程序类加载器
    System.out.println("扩展jar类加载器:" + classLoader.getParent()); // ext classLoader 扩展类加载器 加载<JAVA_HOME>\lib\ext中的类库
    System.out.println("启动jar类加载器:" + classLoader.getParent().getParent()); // root classLoader 加载<JAVA_HOME>\lib中的类库
    System.out.println("ExtClassLoader ext path" + System.getProperty("java.ext.dirs"));
    System.out.println("AppClassLoader class path:" + System.getProperty("java.class.path"));
  }

  /**
   * 需要保证FileClass这个类事先不能被AppClassLoader ExtClassLoader 启动类加载器 加载
   */
  private static void defineDifferentLoader() {
    String rootDir ="/Users/liuzhuang/Maven/project/projectb/class";
    FileSystemClassLoader fileSystemClassLoader1 = new FileSystemClassLoader(rootDir);
    FileSystemClassLoader fileSystemClassLoader2 = new  FileSystemClassLoader(rootDir);
    try {
      Class<?> fileClass1 = fileSystemClassLoader1.loadClass("com.liuzhuang.projectb.FileClass");
      Object fileObject1 = fileClass1.newInstance();
    
      Class<?> fileClass2 = fileSystemClassLoader2.loadClass("com.liuzhuang.projectb.FileClass");
      Object fileObject2 = fileClass2.newInstance();

       Method setSampleMethod = fileClass1.getMethod("setSample", java.lang.Object.class);
       setSampleMethod.invoke(fileObject1, fileObject2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
