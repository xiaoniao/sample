/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.test.zookeeper.rpc.rpc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 简单的RPC协议的方法的注解
 * @author zhangwei_david
 * @version $Id: STRService.java, v 0.1 2014年12月31日 下午4:33:14 zhangwei_david Exp $
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {

    String value() default "";

    Class<?> inf();
}
