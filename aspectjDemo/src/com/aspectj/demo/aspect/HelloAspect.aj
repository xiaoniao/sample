package com.aspectj.demo.aspect;

// 定义切面
public aspect HelloAspect {

	// com.aspectj.demo.test.HelloWorld.main 拦截 HelloWorld的main方法
	// main拦截所有的main方法
	
	// 切入点
	pointcut HelloWorldPointCut(int i) : call (* main(int)) && args(i) && !within(com.aspectj.demo.test.HelloAspectDemo);

	// 通知类型
	before(int i) : HelloWorldPointCut(i) {
		System.out.println("HelloWorld!" + i);
		System.out.println("entering:" + thisJoinPoint.getSourceLocation());// 打印代码的位置
	}
	
	// around通知 是替换方法体
	int around(int i) : HelloWorldPointCut(i) {
		System.out.println("around entering:" + thisJoinPoint.getSourceLocation());
		return i * 3;
	}
	
	/**
	 * after
	 */
	pointcut agePoint(int i) : call (* age(int)) && args(i);
	
	pointcut throwTestPoint() : call (* throwTest());

	after(int i) returning() : agePoint(i) {
		System.out.println("return normally with:" + i);
	}
	
	after() throwing(Exception e) : throwTestPoint() {
		System.out.println("throw a exception:" + e.getMessage());
	}
}
