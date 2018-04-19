package com.example.springlearn.controller;

import com.example.springlearn.model.Goods;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Valid adj. 有效的；有根据的；合法的；正当的 Validated adj. 经过验证的 v. 确认（validate的过去式及过去分词形式）；使生效
 *
 * @Valid
 * @Validated 不能在方法上用
 *
 * Created by liuzz on 2018/04/19
 */
@RestController
@RequestMapping("/goods")
public class ValidController {

    /********************************* Valid **************************************/

    @RequestMapping("/list1")
    public List<Goods> list1(@Valid @RequestBody Goods goods) {
        return new ArrayList<>();
    }

    @RequestMapping("/list2")
    public List<Goods> list2(@Valid @RequestBody Goods goods, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors());
        }
        return new ArrayList<>();
    }

    /********************************* Validated **************************************/

    @RequestMapping("/list3")
    public List<Goods> list3(@Validated @RequestBody Goods goods) {
        return new ArrayList<>();
    }

    @RequestMapping("/list4")
    public List<Goods> list4(@Validated @RequestBody Goods goods, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors());
        }
        return new ArrayList<>();
    }
}
