package com.test.tools.utils;

/**
 * 比较转换对象
 *
 * Created by liuzz on 2018/01/19
 */
public interface Compare<DO, VO> {

    /**
     * 比较两个对象是否代表一个业务对象
     *
     * @param d
     * @param v
     * @return
     */
    default boolean isEquals(DO d, VO v) {
        return false;
    }

    /**
     * 判断DO是否需要更新
     *
     * @param d
     * @param v
     * @return
     */
    default boolean isNeedUpdate(DO d, VO v) {
        return true;
    }

    /**
     * 转换对象 VO->DO
     *
     * @param v
     * @return
     */
    default DO transformVo2Do(VO v) {
        return null;
    }
}
