package com.example.masterslavespring.masterslave;

/**
 * Created by liuzz on 2018/06/07
 */
public @interface DataSource {

    String name() default DataSource.master;

    String master = "dataSourceMaster";

    String slave1 = "dataSourceSlave1";

    String slave2 = "dataSourceSlave2";
}