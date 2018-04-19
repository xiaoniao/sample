package com.example.javaLearn;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

/**
 * Comparator n. [仪] 比较仪；比测仪
 *
 * 在不修改原有类的情况下进行比较
 *
 *
 * 实现比较功能，要不实现Comparable接口要不实现Comparator。
 *
 * 两个参数
 */
public class ComparatorTest {

    @Test
    public void testBasic() {
        Comparator<Product> comparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getId() - o2.getId();
            }
        };

        Product product = new Product();
        product.setId(1);

        Product product2 = new Product();
        product2.setId(2);

        System.out.println(comparator.compare(product, product2));
    }

    @Test
    public void testComparator() {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product p = new Product();
            p.setId(i);
            list.add(p);
        }
        list.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getId() - o2.getId();
            }
        });
        list.forEach(s -> {
            System.out.println(s.getId());
        });
    }

    private static class Product {

        Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    @Test
    public void testUser() {
        Comparator comparator = Collator.getInstance(java.util.Locale.CHINA);
        String[] arrStrings = {"乔峰", "郭靖", "杨过", "张无忌", "韦小宝"};
        Arrays.sort(arrStrings, comparator);
        for (String s : arrStrings) {
            System.out.println(s);
        }
    }
}
