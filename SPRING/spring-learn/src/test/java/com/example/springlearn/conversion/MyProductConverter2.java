package com.example.springlearn.conversion;

import com.example.springlearn.model.Product;
import com.google.gson.Gson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * Created by liuzz on 2018/04/26
 */
public class MyProductConverter2 implements Converter<String, Product> {

    @Nullable
    @Override
    public Product convert(String s) {
        return new Gson().fromJson(s, Product.class);
    }
}
