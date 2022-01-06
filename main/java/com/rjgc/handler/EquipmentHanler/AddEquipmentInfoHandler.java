package com.rjgc.handler.EquipmentHanler;

import com.rjgc.Service.EquipmentService;
import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.EquipmentServiceImpl;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.entity.EquipmentDO;
import com.rjgc.entity.UserDO;
import com.rjgc.equipment.view.AddEquipmentView;
import com.rjgc.equipment.view.EquipmentView;
import com.rjgc.user.view.AddUserView;
import com.rjgc.user.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: zhangxiaofeng
 * Date: 2021/12/31
 * Time: 19:11
 */
public class AddEquipmentInfoHandler implements ActionListener {
    private AddEquipmentView addEquipmentView;
    private EquipmentView equipmentView;
    public AddEquipmentInfoHandler(AddEquipmentView addEquipmentView, EquipmentView equipmentView){
        this.addEquipmentView = addEquipmentView;
        this.equipmentView = equipmentView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        boolean flag = true;
        if("提交".equals(text)){
            EquipmentService equipmentService = new EquipmentServiceImpl();
            EquipmentDO equipmentDO;
            equipmentDO = addEquipmentView.addEquipmentDO();
            boolean addResult = equipmentService.addEquipment(equipmentDO);
            if(addResult){
                // 重新加载表格查到最新数据
                equipmentView.reloadTable();
                // 关闭添加界面
                JOptionPane.showMessageDialog(addEquipmentView, "添加成功");
                addEquipmentView.dispose();
            }else{

                JOptionPane.showMessageDialog(addEquipmentView, "添加失败");
            }
        }
    }
}
