package com.rjgc.manager.view.extend;


import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/30/21
 * @Time: 10:04 PM
 */
public class ManagerViewTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("帐号编号");
        columns.addElement("用户名");
        columns.addElement("密码");
        columns.addElement("拥有者编号");
        columns.addElement("拥有者姓名");
    }
    private ManagerViewTableModel(){
        super(null, columns);
    }
    private static ManagerViewTableModel managerViewTableModel = new ManagerViewTableModel();
    public static ManagerViewTableModel assembleModel(Vector<Vector<Object>> data){
        managerViewTableModel.setDataVector(data, columns);
        return managerViewTableModel;
    }
    public static void updateModel(Vector<Vector<Object>> data){
        managerViewTableModel.setDataVector(data, columns);

    }
    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
