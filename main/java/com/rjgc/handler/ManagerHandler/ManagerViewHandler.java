package com.rjgc.handler.ManagerHandler;

import com.rjgc.Service.ManagerService;
import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.ManagerServiceImpl;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.manager.view.AddManagerView;
import com.rjgc.manager.view.ManagerView;
import com.rjgc.manager.view.UpdateManagerView;
import com.rjgc.user.view.AddUserView;
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
public class ManagerViewHandler implements ActionListener {
    private ManagerView managerView;
    public ManagerViewHandler(ManagerView managerView){
        this.managerView = managerView;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text)){
            new AddManagerView(managerView);
        }else if("修改".equals(text)){
            new UpdateManagerView(managerView);
        }else if("删除".equals(text)){
            int[] selectedManagerIds = managerView.getSelectManagerIds();
            if(selectedManagerIds.length == 0){
                JOptionPane.showMessageDialog(managerView, "请选择要删除的行!");
                return;
            }
            int option = JOptionPane.showConfirmDialog(managerView, "你确定要删除选择的" + selectedManagerIds.length + "行吗?",
                    "确认删除", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                // 确认删除
                ManagerService managerService = new ManagerServiceImpl();
                boolean deleteResult = managerService.deleteManagerInfo(selectedManagerIds);
                if(deleteResult){
                    // 关闭添加界面
                    managerView.reloadTable();
                    JOptionPane.showMessageDialog(managerView, "删除成功");
                }else{

                    JOptionPane.showMessageDialog(managerView, "删除失败");
                }
            }
        }else if("查询".equals(text)){
            managerView.setPageNow(1);
            managerView.reloadTable();
        }else if("上一页".equals(text)){
            managerView.setPageNow(managerView.getPageNow() - 1);
            managerView.reloadTable();
        }else if("下一页".equals(text)){
            managerView.setPageNow(managerView.getPageNow() + 1);
            managerView.reloadTable();
        }
    }
}
