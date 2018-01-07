package com.liuzhuang.io;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) throws IOException, InterruptedException {
    // ByteIO byteIO = new ByteIO();
    // byteIO.write("hello");
    // byteIO.read();

    CharIO charIO = new CharIO();
    // charIO.write();
    charIO.read();
  }
}
