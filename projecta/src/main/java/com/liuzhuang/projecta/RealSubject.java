package com.liuzhuang.projecta;

public class RealSubject implements Subject, BrotherSubject{

  public void init() {
    System.out.println("init");
  }
  
  public void commit() {
    System.out.println("commit");
  }

  public void brother() {
    System.out.println("brother");
  }
}
