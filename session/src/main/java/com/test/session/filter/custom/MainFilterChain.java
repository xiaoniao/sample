package com.test.session.filter.custom;


/**
 * http://www.cnblogs.com/iou123lg/archive/2013/06/02/3113415.html
 */
public class MainFilterChain {

    public static void main(String[] args) {
        CustomApplicationFilterChain fc = new CustomApplicationFilterChain();
        fc.addFilter(new HTMLFilter());
        fc.addFilter(new SensitiveFilter());
        fc.doFilter(null, null);
    }
}
