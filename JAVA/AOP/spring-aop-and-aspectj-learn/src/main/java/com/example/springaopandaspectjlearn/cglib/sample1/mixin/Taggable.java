package com.example.springaopandaspectjlearn.cglib.sample1.mixin;

import java.util.List;

/**
 * https://www.zhihu.com/question/20778853/answer/42943272
 */
public interface Taggable {

    void addTag(int tagId);

    List<Integer> getTags();
}
