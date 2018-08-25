package com.liuzhuang.projectb;

/**
 * 拦截 loadClass 方法
 */
public aspect ClassLoaderAspect {

	// pointcut loadClass(String name, boolean resolve):execution(*
	// loadClass(String , boolean )) && args(name) && args(resolve);
	//
	// before(String name, boolean resolve):loadClass(name, resolve){
	// System.out.println("*******" + name + " resolve:" + resolve);
	// }
	pointcut loadClass():execution(* loadClass(..));

	before():loadClass(){
		System.out.println("*******");
	}
}
