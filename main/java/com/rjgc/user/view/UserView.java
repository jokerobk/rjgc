package com.rjgc.user.view;

import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.handler.UserHandler.UserViewHandler;
import com.rjgc.request.Request;
import com.rjgc.res.TableDTO;
import com.rjgc.user.view.extend.UserViewTable;
import com.rjgc.user.view.extend.UserViewTableModel;
import com.rjgc.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 1:07 PM
 */
public class UserView extends Box {
    JPanel northJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addButton = new JButton("增加");
    JButton updateButton = new JButton("修改");
    JButton delButton = new JButton("删除");
    JTextField searchTextField = new JTextField(15);
    JButton searchButton = new JButton("查询");
    JPanel southJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preButton = new JButton("上一页");
    JButton nextButton = new JButton("下一页");

    UserViewTable userViewTable = new UserViewTable();

    // 设置页码
    private int pageNow = 1;    // 当前第几页
    private int pageSize = 10;  // 一页显示多少条数据库记录
    private final int WIDTH = 1800;
    UserViewHandler userViewHandler;

    public UserView(){
//        super("主界面-健身中心会员管理系统");
        super(BoxLayout.Y_AXIS);
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height, 47);
//        Container contentPane = getContentPane();
        userViewHandler = new UserViewHandler(this);
        // 放置北边的组件
//        layoutNorth(contentPane);
        layoutNorth();
        // 放置中间的组件
//        layoutCenter(contentPane);
        layoutCenter();
        // 放置南边的组件
//        layoutSouth(contentPane);
        layoutSouth();

        // 自定义图标
//        ImageIcon imageIcon = new ImageIcon("/home/xinong/IdeaProjects/课程设计/src/main/resources/tubiao.png");
//        setIconImage(imageIcon.getImage());

        // 根据屏幕设置主界面的大小
        setBounds(bounds);
        // 设置窗体完全充满整个屏幕的可见大小
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(true);
        setVisible(true);
    }

    private void layoutCenter() {
        TableDTO tableDTO = getTableDTO();
        UserViewTableModel userViewTableModel = UserViewTableModel.assembleModel(tableDTO.getData());


        userViewTable.setModel(userViewTableModel);
        userViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(userViewTable);
        this.add(jScrollPane, BorderLayout.CENTER);
        showPreNext(tableDTO.getTotalCount());
    }




    private void layoutSouth() {
        southJPanel.setMaximumSize(new Dimension(WIDTH, 300));
        southJPanel.add(preButton);
        southJPanel.add(nextButton);
        // 增加事件监听
        preButton.addActionListener(userViewHandler);
        nextButton.addActionListener(userViewHandler);
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
        addButton.addActionListener(userViewHandler);
        updateButton.addActionListener(userViewHandler);
        delButton.addActionListener(userViewHandler);
        searchButton.addActionListener(userViewHandler);
        this.add(northJPanel, BorderLayout.NORTH);
    }

    private TableDTO getTableDTO() {
        UsersService usersService = new UserServiceImpl();
        Request request = new Request();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTextField.getText().trim());
        return usersService.retrieveUser(request);
    }

    public void reloadTable(){
        TableDTO tableDTO = getTableDTO();
        UserViewTableModel.updateModel(tableDTO.getData());
        userViewTable.renderRule();
        showPreNext(tableDTO.getTotalCount());
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    public int[] getSelectUserIds(){
        int[] selectedRows = userViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for(int i = 0; i < selectedRows.length; i ++){
            int row = selectedRows[i];
            Object idObj = userViewTable.getValueAt(row, 0);
            ids[i] = Integer.parseInt(idObj.toString());
        }
        return ids;
    }


}
