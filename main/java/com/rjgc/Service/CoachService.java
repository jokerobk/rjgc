package com.rjgc.Service;

import com.rjgc.entity.CoachDO;
import com.rjgc.entity.ManagerDO;
import com.rjgc.request.Request;
import com.rjgc.res.TableDTO;

import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/31/21
 * @Time: 10:55 AM
 */
public interface CoachService {
    TableDTO retrieveCoach(Request request);
    Vector<String> getCoachId();
    Vector<Object> getCoachInfo(Object coachID);
    boolean addCoach(CoachDO coachDO);
    boolean judgeId(Integer id);
    boolean updateCoach(CoachDO coachDO);

    boolean deleteCoachInfo(int[] selectedCoachIds);
}
