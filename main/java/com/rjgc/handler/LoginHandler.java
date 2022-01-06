package com.rjgc.handler;

import com.rjgc.Service.impl.AdminServiceImpl;
import com.rjgc.entity.AdminDO;
import com.rjgc.handler.ManagerHandler.ManagerViewHandler;
import com.rjgc.main.view.MainView;
import com.rjgc.main.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 9:42 AM
 */
public class LoginHandler extends KeyAdapter implements ActionListener {
    LoginView loginView;

    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("重置".equals(text)) {
            loginView.getUserTextField().setText("");
            loginView.getPwdField().setText("");
        }else if("登陆".equals(text)){
            login();
        }
    }

    private void login() {
        String userName = loginView.getUserTextField().getText();
        char[] chars = loginView.getPwdField().getPassword();
        if (userName == null || "".equals(userName.trim()) || chars == null){
            JOptionPane.showMessageDialog(loginView, "用户名密码不能为空");
            return;
        }
        String pwd = new String(chars);
        // 查询数据库
        AdminServiceImpl adminService = new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();
        adminDO.setUserName(userName);
        adminDO.setPwd(pwd);
        boolean flag = adminService.validateAdmin(adminDO);
        if(flag){
            MainView mainView = new MainView();
            mainView.setUserName(userName);

            loginView.dispose();
        }else{
            JOptionPane.showMessageDialog(loginView, "用户名密码错误");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER == e.getKeyCode()){
            login();
        }
    }
}
