package com.example.JVMlearn.classs;

/**
 *
 * 尽量使用基本类型不适用包装类型
 *
 * 使用包装类型多了一步把基本类型转换成包装类型的指令
 *
 * Created by liuzhuang on 2018/8/16.
 */
public class IntegerClass {


    /**
         0: sipush        1000
         3: istore_1
         4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         7: iload_1
         8: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
         11: return
     */
    public void f1() {
        int age = 1000;
        System.out.println(age);
    }

    /**
         0: sipush        1000
         3: invokestatic  #4                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
         6: astore_1
         7: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        10: aload_1
        11: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
        14: return
     */
    public void f2() {
        Integer age = 1000;
        System.out.println(age);
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));



    }
}
