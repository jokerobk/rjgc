package com.rjgc.handler.EquipmentHanler;

import com.rjgc.Service.EquipmentService;
import com.rjgc.Service.impl.EquipmentServiceImpl;
import com.rjgc.entity.EquipmentDO;
import com.rjgc.equipment.view.EquipmentView;
import com.rjgc.equipment.view.UpdateEquipmentView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: zhangxiaofeng
 * Date: 2021/12/31
 * Time: 19:11
 */
public class UpdateEquipmentInfoHandler implements ActionListener {
    private UpdateEquipmentView updateEquipmentView;
    private EquipmentView equipmentView;
    public UpdateEquipmentInfoHandler(UpdateEquipmentView updateEquipmentView, EquipmentView equipmentView){
        this.updateEquipmentView = updateEquipmentView;
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
            equipmentDO = updateEquipmentView.updateEquipmentDO();
            boolean updateResult = equipmentService.updateEquipment(equipmentDO);
            if(updateResult){
                // 关闭添加界面
                equipmentView.reloadTable();
                JOptionPane.showMessageDialog(updateEquipmentView, "修改成功");
                updateEquipmentView.dispose();
            }else{
                JOptionPane.showMessageDialog(updateEquipmentView, "修改失败");
            }
        }
    }
}
