package com.rjgc.Service;

import com.rjgc.entity.ManagerDO;
import com.rjgc.entity.UserDO;
import com.rjgc.request.Request;
import com.rjgc.res.TableDTO;

import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/30/21
 * @Time: 10:12 PM
 */
public interface ManagerService {
    TableDTO retrieveManager(Request request);
    Vector<String> getManagerId();
    Vector<Object> getManagerInfo(Object managerID);
    boolean addManager(ManagerDO managerDO);
    boolean judgeId(Integer id);
    boolean updateManager(ManagerDO managerDO);

    boolean deleteManagerInfo(int[] selectedManagerIds);
}
