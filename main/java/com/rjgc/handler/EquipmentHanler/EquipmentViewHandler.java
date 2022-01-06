package com.rjgc.handler.EquipmentHanler;

import com.rjgc.Service.EquipmentService;
import com.rjgc.Service.impl.EquipmentServiceImpl;
import com.rjgc.equipment.view.AddEquipmentView;
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
public class EquipmentViewHandler implements ActionListener {
    private EquipmentView equipmentView;
    public EquipmentViewHandler(EquipmentView equipmentView){
        this.equipmentView = equipmentView;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text)){
            new AddEquipmentView(equipmentView);
        }else if("修改".equals(text)){
            new UpdateEquipmentView(equipmentView);
        }else if("删除".equals(text)){
            int[] selectedEquipmentIds = equipmentView.getSelectEquipmentIds();
            if(selectedEquipmentIds.length == 0){
                JOptionPane.showMessageDialog(equipmentView, "请选择要删除的行!");
                return;
            }
            int option = JOptionPane.showConfirmDialog(equipmentView, "你确定要删除选择的" + selectedEquipmentIds.length + "行吗?",
                    "确认删除", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                // 确认删除
                EquipmentService equipmentsService = new EquipmentServiceImpl();
                boolean deleteResult = equipmentsService.deleteEquipmentInfo(selectedEquipmentIds);
                if(deleteResult){
                    // 关闭添加界面
                    equipmentView.reloadTable();
                    JOptionPane.showMessageDialog(equipmentView, "删除成功");
                }else{

                    JOptionPane.showMessageDialog(equipmentView, "删除失败");
                }
            }
        }else if("查询".equals(text)){
            equipmentView.setPageNow(1);
            equipmentView.reloadTable();
        }else if("上一页".equals(text)){
            equipmentView.setPageNow(equipmentView.getPageNow() - 1);
            equipmentView.reloadTable();
        }else if("下一页".equals(text)){
            equipmentView.setPageNow(equipmentView.getPageNow() + 1);
            equipmentView.reloadTable();
        }
    }
}
