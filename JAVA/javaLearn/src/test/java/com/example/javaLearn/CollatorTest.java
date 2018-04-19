package com.example.javaLearn;

import java.text.Collator;
import java.util.Locale;
import org.junit.Test;

/**
 * 针对自然语言进行排序和搜索
 *
 * doc : https://docs.oracle.com/javase/7/docs/api/java/text/Collator.html
 *
 * Collator n. 整理器；核对人；校对机
 * Locale n. 场所，现场
 *
 */
public class CollatorTest {

    @Test
    public void testLocale() {
        Locale[] locals = Collator.getAvailableLocales();
        for (Locale local : locals) {
            System.out.println(local);
        }

    }

    @Test
    public void testCollator() {
        Collator collator = Collator.getInstance(Locale.CHINA);
        int result = collator.compare("阿三", "比特币");
        System.out.println(result);

    }
}
