package com.rjgc.equipment.view.extend;

import com.rjgc.util.ViewCellRenderUtil;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

/**
 * Author: zhangxiaofeng
 * Date: 2021/12/31
 * Time: 18:48
 */
public class EquipmentViewTable extends JTable {
    public EquipmentViewTable(){
        // 设置表头
        JTableHeader jTableHeader = getTableHeader();
        jTableHeader.setFont(new Font(null, Font.BOLD, 16));
        jTableHeader.setForeground(Color.RED);
        // 表格体
        setFont(new Font(null, Font.PLAIN, 14));
        setForeground(Color.BLACK);
        setGridColor(Color.BLACK);
        setRowHeight(47);
        // 设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }


    public void renderRule(){
        Vector<String> columns = EquipmentViewTableModel.getColumns();
        ViewCellRenderUtil render = new ViewCellRenderUtil();
        for (int i = 0; i < columns.size(); i ++){
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);
            if( i == 0 ){
                column.setPreferredWidth(150);
                column.setMaxWidth(150);
                column.setResizable(false);
            }
        }
    }
}
