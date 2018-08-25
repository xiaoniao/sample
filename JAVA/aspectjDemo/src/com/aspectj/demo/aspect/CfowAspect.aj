package com.aspectj.demo.aspect;

public aspect CfowAspect {

	pointcut barPoint() : execution(* bar(..));

	pointcut fooPoint() : execution(* foo(..));

	// cflow 方法控制流
	pointcut barcfow() : cflow(barPoint()) && !within(CfowAspect);

	pointcut fooInbar() : barcfow() && fooPoint();

	before() : barcfow() {
		System.out.println("Enter:" + thisJoinPoint); // 展示方法栈执行流程
	}
}
