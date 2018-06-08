package com.example.masterslavespring.masterslave;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * Created by liuzz on 2018/06/07
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }
}
