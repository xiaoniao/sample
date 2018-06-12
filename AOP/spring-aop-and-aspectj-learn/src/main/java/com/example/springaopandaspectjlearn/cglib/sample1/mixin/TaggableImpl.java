package com.example.springaopandaspectjlearn.cglib.sample1.mixin;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.zhihu.com/question/20778853/answer/42943272
 *
 * 这里使用组合模式，在 TaggableImpl 中实现打标签的逻辑，然后让 Post 类和 TaggableImpl 类都实现 Taggable 接口，Post 类中创建一个 TaggableImpl 实例并在实现 Taggable 时将相应方法调用委托过去。
 */
public class TaggableImpl implements Taggable {

    private Entity target;

    public TaggableImpl(Entity target) {
        this.target = target;
    }

    @Override
    public void addTag(int tagId) {
        int id = target.getId();
        int kind = target.getKind();
        System.out.println("insert into ... values " + id + ", " + kind + ", " + tagId + ")");
    }

    @Override
    public List<Integer> getTags() {
        return new ArrayList<>();
    }
}
