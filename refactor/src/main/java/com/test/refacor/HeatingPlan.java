package com.test.refacor;

/**
 * 加热计划
 *
 * Created by liuzz on 2018/01/30
 */
public class HeatingPlan {

    private TempRange tempRange;

    boolean withinRange(int low, int high) {
        return (low >= tempRange.getLow()) && (high <= tempRange.getHigh());
    }
}
