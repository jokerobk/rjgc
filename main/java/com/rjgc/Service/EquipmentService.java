package com.rjgc.Service;

import com.rjgc.entity.EquipmentDO;
import com.rjgc.entity.UserDO;
import com.rjgc.request.Request;
import com.rjgc.res.TableDTO;

import java.util.Vector;

/**
 * Author: zhangxiaofeng
 * Date: 2021/12/31
 * Time: 19:20
 */
public interface EquipmentService {
    TableDTO retrieveEquipment(Request request);
    Vector<String> getEquipmentId();
    Vector<Object> getEquipmentInfo(Object equipmentID);
    boolean addEquipment(EquipmentDO equipmentDO);
    boolean judgeId(Integer id);
    boolean updateEquipment(EquipmentDO equipmentDO);

    boolean deleteEquipmentInfo(int[] selectedEquipmentIds);
}
