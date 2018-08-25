package com.example.JVMlearn.dynamic;

import java.util.List;

/**
 * Created by liuzhuang on 2018/8/18.
 */
public interface DaoMapper {

    List<String> listNameByIds(List<Integer> ids);
}
