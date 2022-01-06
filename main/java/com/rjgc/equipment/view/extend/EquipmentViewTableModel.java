package com.rjgc.equipment.view.extend;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * Author: zhangxiaofeng
 * Date: 2021/12/31
 * Time: 18:51
 */
public class EquipmentViewTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("器材编号");
        columns.addElement("器材名称");
        columns.addElement("购买日期");
        columns.addElement("购买商家");
        columns.addElement("数量");
        columns.addElement("价格(单价，元)");
        columns.addElement("总价");
    }
    private EquipmentViewTableModel(){
        super(null, columns);
    }
    private static EquipmentViewTableModel equipmentViewTableModel = new EquipmentViewTableModel();
    public static EquipmentViewTableModel assembleModel(Vector<Vector<Object>> data){
        equipmentViewTableModel.setDataVector(data, columns);
        return equipmentViewTableModel;
    }
    public static void updateModel(Vector<Vector<Object>> data){
        equipmentViewTableModel.setDataVector(data, columns);

    }
    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
