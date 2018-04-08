package com.example.springannotaionlearn.importAndimportSource.model;

/**
 * Created by liuzz on 2018/04/04
 */
public class CDPlayer {

    private CompactDisc cd;

    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}
