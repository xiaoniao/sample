package com.test.tools.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 比较集合的差(用于和数据库对象比较，进行增删改)
 *
 * VO 业务对象、 DO 数据库对象
 *
 * Created by liuzz on 2018/01/18
 */
public class CompareUtils<VO, DO> {

    /**
     * 获取要删除的DO集合
     *
     * @param doList
     * @param voList
     * @param compare
     * @return
     */
    public List<DO> getDeleteDoList(List<DO> doList, List<VO> voList, Compare<DO, VO> compare) {
        if (doList == null || voList == null || compare == null) {
            return null;
        }
        return doList.stream().filter(d -> voList.stream().noneMatch(v -> compare.isEquals(d, v)))
                .collect(Collectors.toList());
    }

    /**
     * 获取要更新的DO集合
     *
     * @param doList
     * @param voList
     * @param compare
     * @return
     */
    public List<DO> getUpdateDoList(List<DO> doList, List<VO> voList, Compare<DO, VO> compare) {
        if (doList == null || voList == null || compare == null) {
            return null;
        }
        return doList.stream().filter(d -> voList.stream()
                .anyMatch(v -> compare.isEquals(d, v) && compare.isNeedUpdate(d, v)))
                .collect(Collectors.toList());
    }

    /**
     * 获取要新增的DO集合
     *
     * @param doList
     * @param voList
     * @param compare
     * @return
     */
    public List<DO> getInsertDoList(List<DO> doList, List<VO> voList, Compare<DO, VO> compare) {
        if (doList == null || voList == null || compare == null) {
            return null;
        }
        return voList.stream().filter(v -> doList.stream().noneMatch(d -> compare.isEquals(d, v)))
                .map(f -> compare.transformVo2Do(f)).collect(Collectors.toList());
    }
}
