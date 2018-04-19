package com.example.value;

/**
 * Created by liuzz on 2018/04/17
 */
public class Goods {

    private Integer id;

    public Goods(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                '}';
    }
}
