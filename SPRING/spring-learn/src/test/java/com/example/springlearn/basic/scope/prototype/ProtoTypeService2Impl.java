package com.example.springlearn.basic.scope.prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 *
 * INTERFACES java 动态代理
 *
 * Created by liuzz on 2018/04/23
 */
@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
public class ProtoTypeService2Impl implements ProtoTypeService2 {

}
