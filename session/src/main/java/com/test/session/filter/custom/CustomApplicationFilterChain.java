package com.test.session.filter.custom;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CustomApplicationFilterChain implements CustomFilterChain {

    private int index;
    private List<CustomFilter> filterList = new ArrayList<>();

    public void addFilter(CustomFilter customFilter) {
        this.filterList.add(customFilter);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) {
        if (filterList.size() == index) {
            return;
        }

        CustomFilter customFilter = filterList.get(index);
        index++;

        customFilter.doFilter(request, response, this);
    }
}
