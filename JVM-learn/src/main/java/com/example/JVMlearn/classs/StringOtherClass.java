package com.example.JVMlearn.classs;

/**
 * Created by liuzhuang on 2018/8/16.
 */
public class StringOtherClass {

    /**
        public class com.example.JVMlearn.classs.StringOtherClass
          minor version: 0
          major version: 52
          flags: ACC_PUBLIC, ACC_SUPER
        Constant pool:
           #1 = Methodref          #5.#18         // java/lang/Object."<init>":()V
           #2 = String             #19            // Hello
           #3 = Fieldref           #4.#20         // com/example/JVMlearn/StringOtherClass.hello:Ljava/lang/String;
           #4 = Class              #21            // com/example/JVMlearn/StringOtherClass
           #5 = Class              #22            // java/lang/Object
           #6 = Utf8               hello
           #7 = Utf8               Ljava/lang/String;
           #8 = Utf8               <init>
           #9 = Utf8               ()V
          #10 = Utf8               Code
          #11 = Utf8               LineNumberTable
          #12 = Utf8               LocalVariableTable
          #13 = Utf8               this
          #14 = Utf8               Lcom/example/JVMlearn/StringOtherClass;
          #15 = Utf8               <clinit>
          #16 = Utf8               SourceFile
          #17 = Utf8               StringOtherClass.java
          #18 = NameAndType        #8:#9          // "<init>":()V
          #19 = Utf8               Hello
          #20 = NameAndType        #6:#7          // hello:Ljava/lang/String;
          #21 = Utf8               com/example/JVMlearn/StringOtherClass
          #22 = Utf8               java/lang/Object
        {
          public static java.lang.String hello;
            descriptor: Ljava/lang/String;
            flags: ACC_PUBLIC, ACC_STATIC

          public com.example.JVMlearn.classs.StringOtherClass();
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
                    0       5     0  this   Lcom/example/JVMlearn/StringOtherClass;

          static {};
            descriptor: ()V
            flags: ACC_STATIC
            Code:
              stack=1, locals=0, args_size=0
                 0: ldc           #2                  // String Hello
                 2: putstatic     #3                  // Field hello:Ljava/lang/String;
                 5: return
              LineNumberTable:
                line 8: 0
        }
     */
    public static String hello = "Hello";
}
