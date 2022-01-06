package com.rjgc.handler.UserHandler;

import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.entity.UserDO;
import com.rjgc.user.view.AddUserView;
import com.rjgc.user.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/30/21
 * @Time: 11:50 AM
 */
public class AddUserInfoHandler implements ActionListener {
    private AddUserView addUserView;
    private UserView userView;
    public AddUserInfoHandler(AddUserView addUserView, UserView userView){
        this.addUserView = addUserView;
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
            userDO = addUserView.addUserDO();
            boolean addResult = usersService.addUser(userDO);
            if(addResult){
                // 重新加载表格查到最新数据
                userView.reloadTable();
                // 关闭添加界面
                JOptionPane.showMessageDialog(addUserView, "添加成功");
                addUserView.dispose();
            }else{

                JOptionPane.showMessageDialog(addUserView, "添加失败");
            }
        }
    }
}
