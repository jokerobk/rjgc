package com.rjgc.Service;

import com.rjgc.entity.UserDO;
import com.rjgc.request.Request;
import com.rjgc.res.TableDTO;

import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 4:18 PM
 */
public interface UsersService {
    TableDTO retrieveUser(Request request);
    Vector<String> getUserId();
    Vector<Object> getUserInfo(Object userID);
    boolean addUser(UserDO userDO);
    boolean judgeId(Integer id);
    boolean updateUser(UserDO userDO);

    boolean deleteUserInfo(int[] selectedUserIds);
}
