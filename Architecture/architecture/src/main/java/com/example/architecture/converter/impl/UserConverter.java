package com.example.architecture.converter.impl;

import com.example.architecture.converter.Converter;
import com.example.architecture.domain.UserDO;
import com.example.architecture.vo.UserVO;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;

/**
 * Created by liuzz on 2018/03/19
 */
public class UserConverter implements Converter<UserDO, UserVO> {

    @Override
    public UserVO sourceToTarget(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        UserVO result = new UserVO();
        BeanUtils.copyProperties(userDO, result);
        result.setAddress(userDO.getLocation());
        return result;
    }

    @Override
    public UserDO targetToSource(UserVO userVO) {
        if (userVO == null) {
            return null;
        }
        UserDO result = new UserDO();
        BeanUtils.copyProperties(userVO, result);
        result.setLocation(userVO.getAddress());
        return result;
    }

    @Override
    public UserDO convertSourceWithMap(UserDO userDO, Map<String, Object> map) {
        userDO.setLocation((String) map.get("address"));
        return userDO;
    }

    @Override
    public UserVO convertTargetWithMap(UserVO userVO, Map<String, Object> map) {
        userVO.setAddress((String) map.get("address"));
        return userVO;
    }
}
