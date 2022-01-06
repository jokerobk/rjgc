package com.rjgc.handler.UserHandler;

import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.user.view.AddUserView;
import com.rjgc.user.view.UpdateUserView;
import com.rjgc.user.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 10:41 PM
 */
public class UserViewHandler implements ActionListener {
    private UserView userView;
    public UserViewHandler(UserView userView){
        this.userView = userView;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text)){
            new AddUserView(userView);
        }else if("修改".equals(text)){
            new UpdateUserView(userView);
        }else if("删除".equals(text)){
            int[] selectedUserIds = userView.getSelectUserIds();
            if(selectedUserIds.length == 0){
                JOptionPane.showMessageDialog(userView, "请选择要删除的行!");
                return;
            }
            int option = JOptionPane.showConfirmDialog(userView, "你确定要删除选择的" + selectedUserIds.length + "行吗?",
                    "确认删除", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                // 确认删除
                UsersService usersService = new UserServiceImpl();
                boolean deleteResult = usersService.deleteUserInfo(selectedUserIds);
                if(deleteResult){
                    // 关闭添加界面
                    userView.reloadTable();
                    JOptionPane.showMessageDialog(userView, "删除成功");
                }else{

                    JOptionPane.showMessageDialog(userView, "删除失败");
                }
            }
        }else if("查询".equals(text)){
            userView.setPageNow(1);
            userView.reloadTable();
        }else if("上一页".equals(text)){
            userView.setPageNow(userView.getPageNow() - 1);
            userView.reloadTable();
        }else if("下一页".equals(text)){
            userView.setPageNow(userView.getPageNow() + 1);
            userView.reloadTable();
        }
    }
}
