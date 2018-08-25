package com.example.JVMlearn.classs;

/**
 * Created by liuzhuang on 2018/8/16.
 */
public class StringClass {

    /**
        public class com.example.JVMlearn.classs.StringClass
          minor version: 0
          major version: 52
          flags: ACC_PUBLIC, ACC_SUPER
        Constant pool:
           #1 = Methodref          #8.#23         // java/lang/Object."<init>":()V
           #2 = String             #24            // hello
           #3 = String             #25            // world
           #4 = String             #26            // helloworld
           #5 = Fieldref           #27.#28        // java/lang/System.out:Ljava/io/PrintStream;
           #6 = Methodref          #29.#30        // java/io/PrintStream.println:(Ljava/lang/String;)V
           #7 = Class              #31            // com/example/JVMlearn/StringClass
           #8 = Class              #32            // java/lang/Object
           #9 = Utf8               <init>
          #10 = Utf8               ()V
          #11 = Utf8               Code
          #12 = Utf8               LineNumberTable
          #13 = Utf8               LocalVariableTable
          #14 = Utf8               this
          #15 = Utf8               Lcom/example/JVMlearn/StringClass;
          #16 = Utf8               f1
          #17 = Utf8               x
          #18 = Utf8               Ljava/lang/String;
          #19 = Utf8               y
          #20 = Utf8               z
          #21 = Utf8               SourceFile
          #22 = Utf8               StringClass.java
          #23 = NameAndType        #9:#10         // "<init>":()V
          #24 = Utf8               hello
          #25 = Utf8               world
          #26 = Utf8               helloworld
          #27 = Class              #33            // java/lang/System
          #28 = NameAndType        #34:#35        // out:Ljava/io/PrintStream;
          #29 = Class              #36            // java/io/PrintStream
          #30 = NameAndType        #37:#38        // println:(Ljava/lang/String;)V
          #31 = Utf8               com/example/JVMlearn/StringClass
          #32 = Utf8               java/lang/Object
          #33 = Utf8               java/lang/System
          #34 = Utf8               out
          #35 = Utf8               Ljava/io/PrintStream;
          #36 = Utf8               java/io/PrintStream
          #37 = Utf8               println
          #38 = Utf8               (Ljava/lang/String;)V
        {
          public com.example.JVMlearn.classs.StringClass();
            descriptor: ()V
            flags: ACC_PUBLIC
            Code:
              stack=1, locals=1, args_size=1
                 0: aload_0
                 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
                 4: return
              LineNumberTable:
                line 6: 0
              LocalVariableTable:
                Start  Length  Slot  Name   Signature
                    0       5     0  this   Lcom/example/JVMlearn/StringClass;

          public void f1();
            descriptor: ()V
            flags: ACC_PUBLIC
            Code:
              stack=2, locals=4, args_size=1
                 0: ldc           #2                  // String hello
                 2: astore_1
                 3: ldc           #3                  // String world
                 5: astore_2
                 6: ldc           #4                  // String helloworld
                 8: astore_3
                 9: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
                12: aload_3
                13: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
                16: return
              LineNumberTable:
                line 9: 0
                line 10: 3
                line 11: 6
                line 12: 9
                line 13: 16
              LocalVariableTable:
                Start  Length  Slot  Name   Signature
                    0      17     0  this   Lcom/example/JVMlearn/StringClass;
                    3      14     1     x   Ljava/lang/String;
                    6      11     2     y   Ljava/lang/String;
                    9       8     3     z   Ljava/lang/String;
        }
     */
    public void f1() {
        final String x = "hello";
        final String y = "world";
        String z = x + y;
        System.out.println(z);
    }

    /**
        public class com.example.JVMlearn.classs.StringClass
          minor version: 0
          major version: 52
          flags: ACC_PUBLIC, ACC_SUPER
        Constant pool:
           #1 = Methodref          #14.#30        // java/lang/Object."<init>":()V
           #2 = String             #31            // hello
           #3 = String             #32            // world
           #4 = String             #33            // helloworld
           #5 = Fieldref           #34.#35        // java/lang/System.out:Ljava/io/PrintStream;
           #6 = Methodref          #36.#37        // java/io/PrintStream.println:(Ljava/lang/String;)V
           #7 = String             #38            // java
           #8 = String             #39            // jvm
           #9 = Class              #40            // java/lang/StringBuilder
          #10 = Methodref          #9.#30         // java/lang/StringBuilder."<init>":()V
          #11 = Methodref          #9.#41         // java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
          #12 = Methodref          #9.#42         // java/lang/StringBuilder.toString:()Ljava/lang/String;
          #13 = Class              #43            // com/example/JVMlearn/StringClass
          #14 = Class              #44            // java/lang/Object
          #15 = Utf8               <init>
          #16 = Utf8               ()V
          #17 = Utf8               Code
          #18 = Utf8               LineNumberTable
          #19 = Utf8               LocalVariableTable
          #20 = Utf8               this
          #21 = Utf8               Lcom/example/JVMlearn/StringClass;
          #22 = Utf8               f1
          #23 = Utf8               x
          #24 = Utf8               Ljava/lang/String;
          #25 = Utf8               y
          #26 = Utf8               z
          #27 = Utf8               f2
          #28 = Utf8               SourceFile
          #29 = Utf8               StringClass.java
          #30 = NameAndType        #15:#16        // "<init>":()V
          #31 = Utf8               hello
          #32 = Utf8               world
          #33 = Utf8               helloworld
          #34 = Class              #45            // java/lang/System
          #35 = NameAndType        #46:#47        // out:Ljava/io/PrintStream;
          #36 = Class              #48            // java/io/PrintStream
          #37 = NameAndType        #49:#50        // println:(Ljava/lang/String;)V
          #38 = Utf8               java
          #39 = Utf8               jvm
          #40 = Utf8               java/lang/StringBuilder
          #41 = NameAndType        #51:#52        // append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
          #42 = NameAndType        #53:#54        // toString:()Ljava/lang/String;
          #43 = Utf8               com/example/JVMlearn/StringClass
          #44 = Utf8               java/lang/Object
          #45 = Utf8               java/lang/System
          #46 = Utf8               out
          #47 = Utf8               Ljava/io/PrintStream;
          #48 = Utf8               java/io/PrintStream
          #49 = Utf8               println
          #50 = Utf8               (Ljava/lang/String;)V
          #51 = Utf8               append
          #52 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
          #53 = Utf8               toString
          #54 = Utf8               ()Ljava/lang/String;
        {
          public com.example.JVMlearn.classs.StringClass();
            descriptor: ()V
            flags: ACC_PUBLIC
            Code:
              stack=1, locals=1, args_size=1
                 0: aload_0
                 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
                 4: return
              LineNumberTable:
                line 6: 0
              LocalVariableTable:
                Start  Length  Slot  Name   Signature
                    0       5     0  this   Lcom/example/JVMlearn/StringClass;

          public void f1();
            descriptor: ()V
            flags: ACC_PUBLIC
            Code:
              stack=2, locals=4, args_size=1
                 0: ldc           #2                  // String hello
                 2: astore_1
                 3: ldc           #3                  // String world
                 5: astore_2
                 6: ldc           #4                  // String helloworld
                 8: astore_3
                 9: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
                12: aload_3
                13: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
                16: return
              LineNumberTable:
                line 97: 0
                line 98: 3
                line 99: 6
                line 100: 9
                line 101: 16
              LocalVariableTable:
                Start  Length  Slot  Name   Signature
                    0      17     0  this   Lcom/example/JVMlearn/StringClass;
                    3      14     1     x   Ljava/lang/String;
                    6      11     2     y   Ljava/lang/String;
                    9       8     3     z   Ljava/lang/String;

          public void f2();
            descriptor: ()V
            flags: ACC_PUBLIC
            Code:
              stack=2, locals=4, args_size=1
                 0: ldc           #7                  // String java
                 2: astore_1
                 3: ldc           #8                  // String jvm
                 5: astore_2
                 6: new           #9                  // class java/lang/StringBuilder
                 9: dup
                10: invokespecial #10                 // Method java/lang/StringBuilder."<init>":()V
                13: ldc           #7                  // String java
                15: invokevirtual #11                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                18: aload_2
                19: invokevirtual #11                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                22: invokevirtual #12                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
                25: astore_3
                26: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
                29: aload_3
                30: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
                33: return
              LineNumberTable:
                line 104: 0
                line 105: 3
                line 106: 6
                line 107: 26
                line 108: 33
              LocalVariableTable:
                Start  Length  Slot  Name   Signature
                    0      34     0  this   Lcom/example/JVMlearn/StringClass;
                    3      31     1     x   Ljava/lang/String;
                    6      28     2     y   Ljava/lang/String;
                   26       8     3     z   Ljava/lang/String;
        }
        SourceFile: "StringClass.java"
     */
    public void f2() {
        final String x = "java";
        String y = "jvm";
        String z = x + y;
        System.out.println(z);
    }
}
