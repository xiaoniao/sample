package com.example.springlearn.basic.scope.prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 * TARGET_CLASS CGLIB 代理
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class ProtoTypeService {


}
