package com.liuzhuang.projectd;

public class JavaVmStackSOF {

  private int stackLength = 1;

  public void stackLeak() {
    stackLength++;
    stackLeak();
  }

  public static void main(String[] args) {
    JavaVmStackSOF javaVmStackSOF = new JavaVmStackSOF();
    
    try {
      javaVmStackSOF.stackLeak();
    } catch (Throwable e) {
      System.out.println("stack length:" + javaVmStackSOF.stackLength);
      e.printStackTrace();
    }
    
  }
}
