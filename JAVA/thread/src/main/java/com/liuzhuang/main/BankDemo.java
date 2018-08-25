package com.liuzhuang.main;

/**
 * 一个银行家如何将一定数目的资金安全地借给若干个客户，使这些客户既能借到钱完成要干的事，同时银行家又能收回全部资金而不至于破产。银行家就像一个操作系统，客户就像运行的进程，银行家的资金就是系统的资源。<br>
 * 银行家算法需要确保以下四点：<br>
 * 1.当一个顾客对资金的最大需求量不超过银行家现有的资金时就可接纳该顾客；<br>
 * 2.顾客可以分期贷款, 但贷款的总数不能超过最大需求量；<br>
 * 3.当银行家现有的资金不能满足顾客尚需的贷款数额时，对顾客的贷款可推迟支付，但总能使顾客在有限的时间里得到贷款；<br>
 * 4.当顾客得到所需的全部资金后，一定能在有限的时间里归还所有的资金。<br>
 * 
 * 一共有５个进程需要请求资源，有３类资源
 */
public class BankDemo {
  
  // 每个进程所需要的最大资源数
  public static int MAX[][] = { { 7, 5, 3 }, { 3, 2, 2 }, { 9, 0, 2 }, { 2, 2, 2 }, { 4, 3, 3 } };
  
  // 系统拥有的初始资源数
  public static int AVAILABLE[] = { 10, 5, 7 };
  
  // 系统已给每个进程分配的资源数
  public static int ALLOCATION[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
  
  // 每个进程还需要的资源数
  public static int NEED[][] = { { 7, 5, 3 }, { 3, 2, 2 }, { 9, 0, 2 }, { 2, 2, 2 }, { 4, 3, 3 } };
  
  // 每次申请的资源数
  public static int Request[] = { 0, 0, 0 };
  
  // 进程数与资源数
  public static int M = 5, N = 3;
  
  int FALSE = 0;
  int TRUE = 1;
  
  public static void main(String[] args) {
    BankDemo bankDemo = new BankDemo();
    bankDemo.showdata();
  }

  public void showdata() {
    int i, j;
    System.out.print("--系统可用的资源数为:\n");
    for (j = 0; j < N; j++) {
      System.out.print("资源" + j + ":" + AVAILABLE[j] + " ");
    }
    System.out.println();
    System.out.println("--各进程还需要的资源量:");
    for (i = 0; i < M; i++) {
      System.out.print("进程" + i + ":");
      for (j = 0; j < N; j++) {
        System.out.print("资源" + j + ":" + NEED[i][j] + " ");
      }
      System.out.print("\n");
    }
    System.out.print("--各进程已经得到的资源量: \n");
    for (i = 0; i < M; i++) {
      System.out.print("进程");
      System.out.print(i);
      for (j = 0; j < N; j++) {
        System.out.print("资源" + j + ":" + ALLOCATION[i][j] + " ");
      }
      System.out.print("\n");
    }
  }

  // 分配资源，并重新更新各种状态
  public void changdata(int k) {
    int j;
    for (j = 0; j < N; j++) {
      AVAILABLE[j] = AVAILABLE[j] - Request[j];
      ALLOCATION[k][j] = ALLOCATION[k][j] + Request[j];
      NEED[k][j] = NEED[k][j] - Request[j];
    }
  };

  // 回收资源，并重新更新各种状态
  public void rstordata(int k) {
    int j;
    for (j = 0; j < N; j++) {
      AVAILABLE[j] = AVAILABLE[j] + Request[j];
      ALLOCATION[k][j] = ALLOCATION[k][j] - Request[j];
      NEED[k][j] = NEED[k][j] + Request[j];
    }
  };

  // 释放资源
  public void free(int k) {
    for (int j = 0; j < N; j++) {
      AVAILABLE[j] = AVAILABLE[j] + ALLOCATION[k][j];
      System.out.print("释放" + k + "号进程的" + j + "资源!/n");
    }
  }

  public int check0(int k) {
    int j, n = 0;
    for (j = 0; j < N; j++) {
      if (NEED[k][j] == 0)
        n++;
    }
    if (n == 3)
      return 1;
    else
      return 0;
  }

  // 检查安全性函数
  // 所以银行家算法其核心是：保证银行家系统的资源数至少不小于一个客户的所需要的资源数。在安全性检查函数 chkerr() 上由这个方法来实现
  // 这个循环来进行核心判断，从而完成了银行家算法的安全性检查工作。
  public int chkerr(int s) {
    int WORK;
    int FINISH[] = new int[M], temp[] = new int[M];// 保存临时的安全进程序列
    int i, j, k = 0;
    for (i = 0; i < M; i++)
      FINISH[i] = FALSE;
    for (j = 0; j < N; j++) {
      WORK = AVAILABLE[j]; // 第 j 个资源可用数
      i = s;
      // 判断第 i 个进程是否满足条件
      while (i < M) {
        if (FINISH[i] == FALSE && NEED[i][j] <= WORK) {
          WORK = WORK + ALLOCATION[i][j];
          FINISH[i] = TRUE;
          temp[k] = i;
          k++;
          i = 0;
        } else {
          i++;
        }
      }
      for (i = 0; i < M; i++)
        if (FINISH[i] == FALSE) {
          System.out.print("/n 系统不安全!!! 本次资源申请不成功!/n");
          return 1;
        }
    }
    System.out.print("/n 经安全性检查，系统安全，本次分配成功。/n");
    System.out.print("本次安全序列：");
    for (i = 0; i < M - 1; i++) {
      System.out.print("进程" + temp[i] + "->");
    }
    System.out.print("进程" + temp[M - 1]);
    System.out.println("/n");
    return 0;
  }
}
