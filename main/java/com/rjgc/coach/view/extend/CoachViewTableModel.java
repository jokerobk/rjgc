package com.rjgc.coach.view.extend;

import com.rjgc.user.view.extend.UserViewTableModel;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/31/21
 * @Time: 10:53 AM
 */
public class CoachViewTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("教练编号");
        columns.addElement("姓名");
        columns.addElement("性别");
        columns.addElement("联系方式");
        columns.addElement("加入日期");
        columns.addElement("备注");

    }
    private CoachViewTableModel(){
        super(null, columns);
    }
    private static CoachViewTableModel coachViewTableModel = new CoachViewTableModel();
    public static CoachViewTableModel assembleModel(Vector<Vector<Object>> data){
        coachViewTableModel.setDataVector(data, columns);
        return coachViewTableModel;
    }
    public static void updateModel(Vector<Vector<Object>> data){
        coachViewTableModel.setDataVector(data, columns);

    }
    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
