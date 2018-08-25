Classfile /Users/liuzhuang/github/sample/JVM-learn/target/classes/com/example/JVMlearn/StringEqualsClass.class
  Last modified Aug 16, 2018; size 713 bytes
  MD5 checksum 043f82b908350175d1197cdd9412b058
  Compiled from "StringEqualsClass.java"
public class com.example.JVMlearn.StringEqualsClass
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #7.#25         // java/lang/Object."<init>":()V
   #2 = String             #26            // Hello
   #3 = String             #27            // lo
   #4 = Fieldref           #28.#29        // java/lang/System.out:Ljava/io/PrintStream;
   #5 = Methodref          #30.#31        // java/io/PrintStream.println:(Z)V
   #6 = Class              #32            // com/example/JVMlearn/StringEqualsClass
   #7 = Class              #33            // java/lang/Object
   #8 = Utf8               <init>
   #9 = Utf8               ()V
  #10 = Utf8               Code
  #11 = Utf8               LineNumberTable
  #12 = Utf8               LocalVariableTable
  #13 = Utf8               this
  #14 = Utf8               Lcom/example/JVMlearn/StringEqualsClass;
  #15 = Utf8               string
  #16 = Utf8               helloVariable
  #17 = Utf8               Ljava/lang/String;
  #18 = Utf8               loVariable
  #19 = Utf8               StackMapTable
  #20 = Class              #32            // com/example/JVMlearn/StringEqualsClass
  #21 = Class              #34            // java/lang/String
  #22 = Class              #35            // java/io/PrintStream
  #23 = Utf8               SourceFile
  #24 = Utf8               StringEqualsClass.java
  #25 = NameAndType        #8:#9          // "<init>":()V
  #26 = Utf8               Hello
  #27 = Utf8               lo
  #28 = Class              #36            // java/lang/System
  #29 = NameAndType        #37:#38        // out:Ljava/io/PrintStream;
  #30 = Class              #35            // java/io/PrintStream
  #31 = NameAndType        #39:#40        // println:(Z)V
  #32 = Utf8               com/example/JVMlearn/StringEqualsClass
  #33 = Utf8               java/lang/Object
  #34 = Utf8               java/lang/String
  #35 = Utf8               java/io/PrintStream
  #36 = Utf8               java/lang/System
  #37 = Utf8               out
  #38 = Utf8               Ljava/io/PrintStream;
  #39 = Utf8               println
  #40 = Utf8               (Z)V
{
  public com.example.JVMlearn.StringEqualsClass();
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
            0       5     0  this   Lcom/example/JVMlearn/StringEqualsClass;

  public void string();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=3, args_size=1
         0: ldc           #2                  // String Hello
         2: astore_1
         3: ldc           #3                  // String lo
         5: astore_2
         6: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
         9: aload_1
        10: ldc           #2                  // String Hello
        12: if_acmpne     19
        15: iconst_1
        16: goto          20
        19: iconst_0
        20: invokevirtual #5                  // Method java/io/PrintStream.println:(Z)V
        23: return
      LineNumberTable:
        line 9: 0
        line 10: 3
        line 12: 6
        line 17: 23
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      24     0  this   Lcom/example/JVMlearn/StringEqualsClass;
            3      21     1 helloVariable   Ljava/lang/String;
            6      18     2 loVariable   Ljava/lang/String;
      StackMapTable: number_of_entries = 2
        frame_type = 255 /* full_frame */
          offset_delta = 19
          locals = [ class com/example/JVMlearn/StringEqualsClass, class java/lang/String, class java/lang/String ]
          stack = [ class java/io/PrintStream ]
        frame_type = 255 /* full_frame */
          offset_delta = 0
          locals = [ class com/example/JVMlearn/StringEqualsClass, class java/lang/String, class java/lang/String ]
          stack = [ class java/io/PrintStream, int ]
}
SourceFile: "StringEqualsClass.java"
