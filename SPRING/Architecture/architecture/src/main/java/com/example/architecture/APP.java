package com.example.architecture;

import com.example.architecture.converter.Converter;
import com.example.architecture.converter.impl.UserConverter;
import com.example.architecture.domain.UserDO;
import com.example.architecture.vo.UserVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzz on 2018/03/19
 */
public class APP {

    static Converter<UserDO, UserVO> userVOConverter = new UserConverter();

    public static void main(String[] args) {
        testTransformDo2Vo();
        testTransformVo2Do();
        testTransformDo2VoList();
        testTransformVo2DoList();
    }

    private static void testTransformDo2Vo() {
        UserDO userDO = new UserDO();
        userDO.setId(1);
        userDO.setName("jack");
        userDO.setAge(18);
        userDO.setLocation("china hangzhou");
        UserVO userVO = userVOConverter.sourceToTarget(userDO);
        System.out.println(userVO);

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", 1);
        map.put("address", "hello");
        UserVO u = userVOConverter.convertTargetWithMap(userVO, map);
        System.out.println("MAP: " + u);
    }

    private static void testTransformDo2VoList() {
        List<UserDO> userDOList = new ArrayList<>();
        UserDO userDO = new UserDO();
        userDO.setName("jack");
        userDO.setAge(18);
        userDO.setLocation("china hangzhou");
        userDOList.add(userDO);

        List<UserVO> userVOList = userVOConverter.sourceToTargetList(userDOList);
        for (UserVO userVO : userVOList) {
            System.out.println(userVO);
        }
    }

    private static void testTransformVo2Do() {
        UserVO userVO = new UserVO();
        userVO.setName("jack");
        userVO.setAge(18);
        userVO.setAddress("china hangzhou");
        UserDO userDO = userVOConverter.targetToSource(userVO);
        System.out.println(userDO);
    }

    private static void testTransformVo2DoList() {
        List<UserVO> userVOList = new ArrayList<>();
        UserVO userVO = new UserVO();
        userVO.setName("jack");
        userVO.setAge(18);
        userVO.setAddress("china hangzhou");
        userVOList.add(userVO);

        List<UserDO> userDOList = userVOConverter.targetToSourceList(userVOList);
        for (UserDO userDO : userDOList) {
            System.out.println(userDO);
        }
    }
}
