package com.rjgc.handler.ManagerHandler;

import com.rjgc.Service.ManagerService;
import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.ManagerServiceImpl;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.entity.ManagerDO;
import com.rjgc.entity.UserDO;
import com.rjgc.manager.view.AddManagerView;
import com.rjgc.manager.view.ManagerView;
import com.rjgc.user.view.AddUserView;
import com.rjgc.user.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/30/21
 * @Time: 10:12 PM
 */
public class AddManagerInfoHandler implements ActionListener {
    private AddManagerView addManagerView;
    private ManagerView managerView;
    public AddManagerInfoHandler(AddManagerView addManagerView, ManagerView managerView){
        this.managerView = managerView;
        this.addManagerView = addManagerView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        boolean flag = true;
        if("提交".equals(text)){
            ManagerService managerService = new ManagerServiceImpl();
            ManagerDO managerDO;
            managerDO = addManagerView.addManagerDO();

            boolean addResult = managerService.addManager(managerDO);
            if(addResult){
                // 重新加载表格查到最新数据
                managerView.reloadTable();
                // 关闭添加界面
                JOptionPane.showMessageDialog(addManagerView, "添加成功");
                addManagerView.dispose();
            }else{

                JOptionPane.showMessageDialog(addManagerView, "添加失败");
            }
        }
    }
}
