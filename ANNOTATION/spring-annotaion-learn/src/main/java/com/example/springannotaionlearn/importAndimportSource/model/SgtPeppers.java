package com.example.springannotaionlearn.importAndimportSource.model;

/**
 * Created by liuzz on 2018/04/04
 */
public class SgtPeppers implements CompactDisc {

    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing .........");
    }

    public void print() {
        System.out.println("SgtPeppers ........");
    }


}
