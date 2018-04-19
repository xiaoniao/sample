package com.example.javaLearn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * Comparable adj. 可比较的；比得上的
 *
 * java 语言内置排序基本 api , 比较对象需要实现该接口
 *
 * binary insertion sort - http://www.cnblogs.com/yysbolg/p/7449478.html
 *
 * 一个参数
 *
 */
public class ComparableTest {

    @Test
    public void testBasic() {
        Goods goods = new Goods();
        goods.setId(1);

        Goods goods2 = new Goods();
        goods2.setId(2);

        System.out.println(goods.compareTo(goods2));
    }

    @Test
    public void testComparable() {
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Goods goods = new Goods();
            goods.setId(i);
            goodsList.add(goods);
        }
        Collections.sort(goodsList);

        goodsList.forEach(s -> System.out.println(s.getId()));
    }

    private class Goods implements Comparable<Goods> {

        private Integer id;

        @Override
        public int compareTo(Goods o) {
            return this.id - o.getId();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
