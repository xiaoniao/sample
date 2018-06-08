package com.example.masterslavespring.masterslave;

/**
 * Created by liuzz on 2018/06/07
 */
public @interface DataSource {

    String name() default DataSource.master;

    String master = "dataSource1";

    String slave1 = "dataSource2";

    String slave2 = "dataSource3";
}