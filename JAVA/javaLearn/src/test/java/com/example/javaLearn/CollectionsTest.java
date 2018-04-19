package com.example.javaLearn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

/**
 *
 * Reverse
 *      n. 背面；相反；倒退；失败
 *      vt. 颠倒；倒转
 *      adj. 反面的；颠倒的；反身的
 *
 *
 *
 *
 * Created by liuzz on 2018/04/19
 */
public class CollectionsTest {


    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Integer max = Collections.max(list);
        System.out.println(max);

        Integer min = Collections.min(list);
        System.out.println(min);

        Comparator<Integer> comparator = Collections.reverseOrder();
        System.out.println(comparator.compare(1, 2));
    }
}
