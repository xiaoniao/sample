package com.example.springlearn.basic.scope.prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by liuzz on 2018/04/23
 */
@Service
@Scope(value = "prototype")
public class ProtoTypeServiceWithLooKup {

}
