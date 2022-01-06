package com.rjgc.handler.CoachHandler;

import com.rjgc.Service.CoachService;
import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.CoachServiceImpl;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.coach.view.AddCoachView;
import com.rjgc.coach.view.CoachView;
import com.rjgc.entity.CoachDO;
import com.rjgc.entity.UserDO;
import com.rjgc.user.view.AddUserView;
import com.rjgc.user.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/31/21
 * @Time: 10:54 AM
 */
public class AddCoachInfoHandler implements ActionListener {
    private AddCoachView addCoachView;
    private CoachView coachView;
    public AddCoachInfoHandler(AddCoachView addCoachView, CoachView coachView){
        this.addCoachView = addCoachView;
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
            coachDO = addCoachView.addCoachDO();
            boolean addResult = coachService.addCoach(coachDO);
            if(addResult){
                // 重新加载表格查到最新数据
                coachView.reloadTable();
                // 关闭添加界面
                JOptionPane.showMessageDialog(addCoachView, "添加成功");
                addCoachView.dispose();
            }else{

                JOptionPane.showMessageDialog(addCoachView, "添加失败");
            }
        }
    }
}
