package com.rjgc.handler.UserHandler;

import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.entity.UserDO;
import com.rjgc.user.view.UpdateUserView;
import com.rjgc.user.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/30/21
 * @Time: 8:58 PM
 */
public class UpdateUserInfoHandler implements ActionListener {
    private UpdateUserView updateUserView;
    private UserView userView;
    public UpdateUserInfoHandler(UpdateUserView updateUserView, UserView userView){
        this.updateUserView = updateUserView;
        this.userView = userView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        boolean flag = true;
        if("提交".equals(text)){
            UsersService usersService = new UserServiceImpl();
            UserDO userDO;
            userDO = updateUserView.updateUserDO();
            boolean updateResult = usersService.updateUser(userDO);
            if(updateResult){
                // 关闭添加界面
                userView.reloadTable();
                JOptionPane.showMessageDialog(updateUserView, "修改成功");
                updateUserView.dispose();
            }else{
                JOptionPane.showMessageDialog(updateUserView, "修改失败");
            }
        }
    }
}
