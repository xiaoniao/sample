package com.liuzhuang.projectb;

public class Constant {

  //https://www.ibm.com/developerworks/cn/java/j-lo-classloader/index.html
  //http://blog.csdn.net/go_to_learn/article/details/7595630
  //http://www.jianshu.com/p/a8371d26f848
  //http://www.2cto.com/kf/201312/261175.html
  //http://www.tuicool.com/articles/bQFnqmi
  //http://stackoverflow.com/questions/42901291/whether-the-aspectj-support-to-modify-the-jdk-bytecode
  public static final String fileClass =
            "package com.liuzhuang.projectb;"+
            ""+
            "public class FileClass {"+
            ""+
            "  private FileClass instance;"+
            ""+
            "  public void setSample(Object instance) {"+
            "    this.instance = (FileClass) instance;"+
            "  }";
}
