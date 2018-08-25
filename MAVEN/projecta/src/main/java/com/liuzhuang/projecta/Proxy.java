package com.liuzhuang.projecta;

public class Proxy implements Subject{

  private RealSubject realSubject;
  
  public void init() {
    if (realSubject == null) {
      realSubject = new RealSubject();
    }
    realSubject.init();
  }
  
  public void commit() {
    if (realSubject == null) {
      realSubject = new RealSubject();
    }
    realSubject.commit();
  }
}
