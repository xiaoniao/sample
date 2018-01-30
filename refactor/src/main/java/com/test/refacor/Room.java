package com.test.refacor;

/**
 * Created by liuzz on 2018/01/30
 */
public class Room {

    boolean withinPlan(HeatingPlan heatingPlan) {
        int low = daysTempRange().getLow();
        int hight = daysTempRange().getHigh();
        return heatingPlan.withinRange(low, hight);
    }

    TempRange daysTempRange() {
        TempRange tempRange = new TempRange();
        return tempRange;
    }

}
