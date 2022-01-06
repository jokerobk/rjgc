package com.rjgc.handler.CoachHandler;

import com.rjgc.Service.CoachService;

import com.rjgc.Service.impl.CoachServiceImpl;

import com.rjgc.coach.view.AddCoachView;
import com.rjgc.coach.view.CoachView;
import com.rjgc.coach.view.UpdateCoachView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/31/21
 * @Time: 10:55 AM
 */
public class CoachViewHandler implements ActionListener {
    private String userName;
    private CoachView coachView;
    public CoachViewHandler(CoachView coachView){
        this.coachView = coachView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text)){
            new AddCoachView(coachView);
        }else if("修改".equals(text)){
            new UpdateCoachView(coachView);
        }else if("删除".equals(text)){
            int[] selectedCoachIds = coachView.getSelectCoachIds();
            if(selectedCoachIds.length == 0){
                JOptionPane.showMessageDialog(coachView, "请选择要删除的行!");
                return;
            }
            int option = JOptionPane.showConfirmDialog(coachView, "你确定要删除选择的" + selectedCoachIds.length + "行吗?",
                    "确认删除", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                // 确认删除
                CoachService coachService = new CoachServiceImpl();
                boolean deleteResult = coachService.deleteCoachInfo(selectedCoachIds);
                if(deleteResult){
                    // 关闭添加界面
                    coachView.reloadTable();
                    JOptionPane.showMessageDialog(coachView, "删除成功");
                }else{

                    JOptionPane.showMessageDialog(coachView, "删除失败");
                }
            }
        }else if("查询".equals(text)){
            coachView.setPageNow(1);
            coachView.reloadTable();
        }else if("上一页".equals(text)){
            coachView.setPageNow(coachView.getPageNow() - 1);
            coachView.reloadTable();
        }else if("下一页".equals(text)){
            coachView.setPageNow(coachView.getPageNow() + 1);
            coachView.reloadTable();
        }
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
