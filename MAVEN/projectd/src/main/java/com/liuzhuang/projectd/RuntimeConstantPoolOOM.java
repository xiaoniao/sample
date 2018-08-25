package com.liuzhuang.projectd;

public class RuntimeConstantPoolOOM {

  public static void main(String[] args) {
    String string1 = new StringBuilder("计算机").append("软件").toString();
    System.out.println(string1.intern() == string1);

    String string2 = new StringBuilder("ja").append("va").toString();
    System.out.println(string2.intern() == string2);
  }
}
