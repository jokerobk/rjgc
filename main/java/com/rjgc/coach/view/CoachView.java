package com.rjgc.coach.view;

import com.rjgc.Service.CoachService;
import com.rjgc.Service.impl.CoachServiceImpl;
import com.rjgc.coach.view.extend.CoachViewTable;
import com.rjgc.coach.view.extend.CoachViewTableModel;
import com.rjgc.handler.CoachHandler.CoachViewHandler;
import com.rjgc.request.Request;
import com.rjgc.res.TableDTO;
import com.rjgc.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/29/21
 * @Time: 9:56 PM
 */
public class CoachView extends Box {
    JPanel northJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addButton = new JButton("增加");
    JButton updateButton = new JButton("修改");
    JButton delButton = new JButton("删除");
    JTextField searchTextField = new JTextField(15);
    JButton searchButton = new JButton("查询");
    JPanel southJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preButton = new JButton("上一页");
    JButton nextButton = new JButton("下一页");

    CoachViewTable coachViewTable = new CoachViewTable();

    // 设置页码
    private int pageNow = 1;    // 当前第几页
    private int pageSize = 10;  // 一页显示多少条数据库记录
    private final int WIDTH = 1800;
    CoachViewHandler coachViewHandler;
    public CoachView(){
        super(BoxLayout.Y_AXIS);
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height, 47);

        coachViewHandler = new CoachViewHandler(this);
        // 放置北边的组件
        layoutNorth();
        // 放置中间的组件
        layoutCenter();
        // 放置南边的组件
        layoutSouth();


        // 根据屏幕设置主界面的大小
        setBounds(bounds);

        setVisible(true);
    }

    private void layoutCenter() {
        TableDTO tableDTO = getTableDTO();
        CoachViewTableModel coachViewTableModel = CoachViewTableModel.assembleModel(tableDTO.getData());

        coachViewTable.setModel(coachViewTableModel);
        coachViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(coachViewTable);
        this.add(jScrollPane, BorderLayout.CENTER);
        showPreNext(tableDTO.getTotalCount());
    }




    private void layoutSouth() {
        southJPanel.setMaximumSize(new Dimension(WIDTH, 300));
        southJPanel.add(preButton);
        southJPanel.add(nextButton);
        // 增加事件监听
        preButton.addActionListener(coachViewHandler);
        nextButton.addActionListener(coachViewHandler);
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
        // 增加事件监听width
        addButton.addActionListener(coachViewHandler);
        updateButton.addActionListener(coachViewHandler);
        delButton.addActionListener(coachViewHandler);
        searchButton.addActionListener(coachViewHandler);
        this.add(northJPanel, BorderLayout.NORTH);
    }
    // 得到数据表格
    private TableDTO getTableDTO() {
        CoachService coachsService = new CoachServiceImpl();
        Request request = new Request();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTextField.getText().trim());
        return coachsService.retrieveCoach(request);
    }
    // 重新加载表格
    public void reloadTable(){
        TableDTO tableDTO = getTableDTO();
        CoachViewTableModel.updateModel(tableDTO.getData());
        coachViewTable.renderRule();
        showPreNext(tableDTO.getTotalCount());
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }
    // 获取选中的行的id
    public int[] getSelectCoachIds(){
        int[] selectedRows = coachViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for(int i = 0; i < selectedRows.length; i ++){
            int row = selectedRows[i];
            Object idObj = coachViewTable.getValueAt(row, 0);
            ids[i] = Integer.parseInt(idObj.toString());
        }
        return ids;
    }
}
