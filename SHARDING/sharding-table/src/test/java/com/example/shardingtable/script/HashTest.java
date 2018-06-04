package com.example.shardingtable.script;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzz on 2018/06/04
 */
public class HashTest {


    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (long a = Long.MIN_VALUE; a < Long.MAX_VALUE; a++) {
            int code = String.valueOf(a).hashCode();
            if (list.contains(code)) {
                System.out.println("error");
            }
            System.out.println(a + "    ....     " + code);
            list.add(code);
        }
    }
}
