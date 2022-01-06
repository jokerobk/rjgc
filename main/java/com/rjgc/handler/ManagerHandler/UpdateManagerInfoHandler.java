package com.rjgc.handler.ManagerHandler;

import com.rjgc.Service.ManagerService;
import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.ManagerServiceImpl;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.entity.ManagerDO;
import com.rjgc.entity.UserDO;
import com.rjgc.manager.view.ManagerView;
import com.rjgc.manager.view.UpdateManagerView;
import com.rjgc.user.view.UpdateUserView;
import com.rjgc.user.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/30/21
 * @Time: 10:12 PM
 */
public class UpdateManagerInfoHandler implements ActionListener {
    private UpdateManagerView updateManagerView;
    private ManagerView managerView;
    public UpdateManagerInfoHandler(UpdateManagerView updateManagerView, ManagerView managerView){
        this.updateManagerView = updateManagerView;
        this.managerView = managerView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        boolean flag = true;
        if("提交".equals(text)){
            ManagerService managerService = new ManagerServiceImpl();
            ManagerDO managerDO;
            managerDO = updateManagerView.updateManagerDO();
            boolean updateResult = managerService.updateManager(managerDO);
            if(updateResult){
                // 关闭添加界面
                managerView.reloadTable();
                JOptionPane.showMessageDialog(updateManagerView, "修改成功");
                updateManagerView.dispose();
            }else{
                JOptionPane.showMessageDialog(updateManagerView, "修改失败");
            }
        }
    }
}
