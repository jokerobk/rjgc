package com.rjgc.handler.CoachHandler;

import com.rjgc.Service.CoachService;
import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.CoachServiceImpl;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.coach.view.CoachView;
import com.rjgc.coach.view.UpdateCoachView;
import com.rjgc.entity.CoachDO;
import com.rjgc.entity.UserDO;
import com.rjgc.user.view.UpdateUserView;
import com.rjgc.user.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/31/21
 * @Time: 10:55 AM
 */
public class UpdateCoachInfoHandler implements ActionListener {
    private UpdateCoachView updateCoachView;
    private CoachView coachView;
    public UpdateCoachInfoHandler(UpdateCoachView updateCoachView, CoachView coachView) {
        this.updateCoachView = updateCoachView;
        this.coachView = coachView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        boolean flag = true;
        if("提交".equals(text)){
            CoachService coachService = new CoachServiceImpl();
            CoachDO coachDO;
            coachDO = updateCoachView.updateCoachDO();
            boolean updateResult = coachService.updateCoach(coachDO);
            if(updateResult){
                // 关闭添加界面
                coachView.reloadTable();
                JOptionPane.showMessageDialog(updateCoachView, "修改成功");
                updateCoachView.dispose();
            }else{
                JOptionPane.showMessageDialog(updateCoachView, "修改失败");
            }
        }
    }
}
