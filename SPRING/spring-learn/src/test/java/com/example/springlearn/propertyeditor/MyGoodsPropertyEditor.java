package com.example.springlearn.propertyeditor;

import com.example.springlearn.model.Goods;
import com.google.gson.Gson;
import java.beans.PropertyEditorSupport;
import org.springframework.util.StringUtils;

/**
 * Created by liuzz on 2018/04/26
 */
public class MyGoodsPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("================================================");
        if (!StringUtils.isEmpty(text)) {
            setValue(new Gson().fromJson(text, Goods.class));
        }
    }
}
