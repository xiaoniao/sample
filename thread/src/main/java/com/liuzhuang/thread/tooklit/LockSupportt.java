package com.liuzhuang.thread.tooklit;

import java.util.concurrent.locks.LockSupport;

public class LockSupportt {

  public static void main(String[] args) {
    // 由sun.misc.Unsafe UNSAFE操作实现
    LockSupport.park();
  }
}
