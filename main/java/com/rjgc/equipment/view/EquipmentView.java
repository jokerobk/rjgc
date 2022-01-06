package com.rjgc.equipment.view;

import com.rjgc.Service.EquipmentService;
import com.rjgc.Service.impl.EquipmentServiceImpl;
import com.rjgc.equipment.view.extend.EquipmentViewTable;
import com.rjgc.equipment.view.extend.EquipmentViewTableModel;
import com.rjgc.handler.EquipmentHanler.EquipmentViewHandler;
import com.rjgc.request.Request;
import com.rjgc.res.TableDTO;
import com.rjgc.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/29/21
 * @Time: 9:57 PM
 */
public class EquipmentView extends Box {
    JPanel northJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addButton = new JButton("增加");
    JButton updateButton = new JButton("修改");
    JButton delButton = new JButton("删除");
    JTextField searchTextField = new JTextField(15);
    JButton searchButton = new JButton("查询");
    JPanel southJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preButton = new JButton("上一页");
    JButton nextButton = new JButton("下一页");

    EquipmentViewTable equipmentViewTable = new EquipmentViewTable();
    EquipmentViewHandler equipmentViewHandler;

    // 设置页码
    private int pageNow = 1;    // 当前第几页
    private int pageSize = 20;  // 一页显示多少条数据库记录
    private final int WIDTH = 1800;
    public EquipmentView(){
        super(BoxLayout.Y_AXIS);
        equipmentViewHandler = new EquipmentViewHandler(this);
        // 放置北边的组件
        layoutNorth();
        // 放置中间的组件
        layoutCenter();
        // 放置南边的组件
        layoutSouth();

        setBounds(DimensionUtil.getBounds());
        setVisible(true);
    }

    private void layoutCenter() {
        TableDTO tableDTO = getTableDTO();
        EquipmentViewTableModel equipmentViewTableModel = EquipmentViewTableModel.assembleModel(tableDTO.getData());


        equipmentViewTable.setModel(equipmentViewTableModel);
        equipmentViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(equipmentViewTable);
        this.add(jScrollPane, BorderLayout.CENTER);
        showPreNext(tableDTO.getTotalCount());
    }




    private void layoutSouth() {
        southJPanel.setMaximumSize(new Dimension(WIDTH, 120));
        southJPanel.add(preButton);
        southJPanel.add(nextButton);
        preButton.addActionListener(equipmentViewHandler);
        nextButton.addActionListener(equipmentViewHandler);
        this.add(southJPanel, BorderLayout.SOUTH);
    }
    // 设置上一页是否可见
    private void showPreNext(int totalCount){
        preButton.setVisible(pageNow != 1);
        int pageCount = 0;  // 总共有多少页
        if(totalCount % pageSize == 0){
            pageCount = totalCount / pageSize;
        }
        else{
            pageCount = totalCount / pageSize + 1;
        }
        nextButton.setVisible(pageNow != pageCount);
    }

    private void layoutNorth() {
        northJPanel.add(addButton);
        northJPanel.add(updateButton);
        northJPanel.add(delButton);
        northJPanel.add(searchTextField);
        northJPanel.add(searchButton);
        northJPanel.setBackground(Color.pink);
        northJPanel.setMaximumSize(new Dimension(WIDTH, 300));
        addButton.addActionListener(equipmentViewHandler);
        updateButton.addActionListener(equipmentViewHandler);
        delButton.addActionListener(equipmentViewHandler);
        searchButton.addActionListener(equipmentViewHandler);
        this.add(northJPanel, BorderLayout.NORTH);
    }

    private TableDTO getTableDTO() {
        EquipmentService equipmentService = new EquipmentServiceImpl();
        Request request = new Request();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTextField.getText().trim());
        return equipmentService.retrieveEquipment(request);
    }

    public void reloadTable(){
        TableDTO tableDTO = getTableDTO();
        EquipmentViewTableModel.updateModel(tableDTO.getData());
        equipmentViewTable.renderRule();
        showPreNext(tableDTO.getTotalCount());
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    public int[] getSelectEquipmentIds(){
        int[] selectedRows = equipmentViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for(int i = 0; i < selectedRows.length; i ++){
            int row = selectedRows[i];
            Object idObj = equipmentViewTable.getValueAt(row, 0);
            ids[i] = Integer.parseInt(idObj.toString());
        }
        return ids;
    }

}
