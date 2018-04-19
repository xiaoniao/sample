package com.example.springlearn.model;

import javax.validation.constraints.NotNull;

/**
 * Created by liuzz on 2018/04/19
 */
public class Goods {

    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
