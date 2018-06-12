package com.example.springaopandaspectjlearn.cglib.sample1.mixin;

import java.util.List;

/**
 * https://www.zhihu.com/question/20778853/answer/42943272
 */
public class Post implements Entity, Taggable {
    public final static int KIND = 1001;

    private int id;
    private String title;
    private Taggable taggable;

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
        this.taggable = new TaggableImpl(this);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getKind() {
        return KIND;
    }

    @Override
    public void addTag(int tagId) {
        taggable.addTag(tagId);
    }

    @Override
    public List<Integer> getTags() {
        return taggable.getTags();
    }
}
