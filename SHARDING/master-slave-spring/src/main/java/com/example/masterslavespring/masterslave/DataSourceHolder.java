package com.example.masterslavespring.masterslave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzz on 2018/06/07
 */
public class DataSourceHolder {
    private static Logger log = LoggerFactory.getLogger(DataSourceHolder.class);

    private static final ThreadLocal<String> dataSources = new ThreadLocal<>();

    public static void setDataSource(String dataSource) {
        log.info("###############################  setDataSource");
        dataSources.set(dataSource);
    }

    public static String getDataSource() {
        log.info("###############################  getDataSource");
        return dataSources.get();
    }

    public static void clearDataSource() {
        log.info("###############################  clearDataSource");
        dataSources.remove();
    }
}
