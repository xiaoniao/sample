package com.test.ognl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ognl.DefaultClassResolver;
import ognl.DefaultTypeConverter;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * Created by liuzz on 2018/03/27
 */
public class OgnlTest {


    public static void main(String[] args) throws OgnlException {
        User root = new User();
        root.setUserName("hello");
        root.setIcon("");
        Address a = new Address();
        a.setLocation("hz");
        root.setAddress(a);

        Map values = new HashMap();
        values.put("title", "杭州");
        values.put("address", a);

        OgnlContext ognlContext = new OgnlContext(new DefaultMemberAccess(true), new DefaultClassResolver(), new DefaultTypeConverter(), values);
        Object userName = Ognl.getValue("userName", ognlContext, root);
        System.out.println(userName);
        System.out.println(Ognl.getValue("userName.length", ognlContext, root));

        Object address = Ognl.getValue("address", ognlContext, root);
        System.out.println(address);

        Object location = Ognl.getValue("address.location", ognlContext, root);
        System.out.println(location);

        Object isLocationNull = Ognl.getValue("address.location != null", ognlContext, root);
        System.out.println(isLocationNull);

        Object isIconNull = Ognl.getValue("icon != null and icon == ''", ognlContext, root);
        System.out.println(isIconNull);

        List<User> userList = new ArrayList<>();
        userList.add(root);
        System.out.println(Ognl.getValue("size()", ognlContext, userList));


        // # 标识访问上下文对象
        System.out.println(Ognl.getValue("#title", ognlContext, root));
        System.out.println(Ognl.getValue("#address", ognlContext, root));


        // 设值
        Ognl.getValue("setUserName(#title)", ognlContext, root);
        System.out.println(Ognl.getValue("userName", ognlContext, root));

        Ognl.setValue("setUserName", ognlContext, root, "上海");
        System.out.println(Ognl.getValue("userName", ognlContext, root));



        // 集合
        Map<String, List<User>> map = new HashMap<>();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName("userName" + i);
            users.add(user);
        }
        map.put("list", users);
        System.out.println(Ognl.getValue("list.{#this.userName}", ognlContext, map));
        System.out.println(Ognl.getValue("list.{userName + '-' + icon}", ognlContext, map));
        System.out.println(Ognl.getValue("list.{? userName.contains(\"8\")}", ognlContext, map)); // 所有
        System.out.println(Ognl.getValue("list.{^ userName.contains(\"8\")}", ognlContext, map)); // 第一个
        System.out.println(Ognl.getValue("list.{$ userName.contains(\"8\")}", ognlContext, map)); // 最后一个

        // 构造
        System.out.println(Ognl.getValue("{'a', 'b'}", ognlContext, map));
        System.out.println(Ognl.getValue("#{'key':'value'}", ognlContext, map));
        User user = (User) Ognl.getValue("new com.test.ognl.User()", ognlContext, map);
        user.setUserName("ss");
        System.out.println(user);


    }
}
