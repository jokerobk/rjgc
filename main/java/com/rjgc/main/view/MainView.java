package com.rjgc.main.view;

import com.rjgc.coach.view.CoachView;
import com.rjgc.component.MyRender;
import com.rjgc.entity.AdminDO;
import com.rjgc.equipment.view.EquipmentView;
import com.rjgc.handler.LoginHandler;
import com.rjgc.manager.view.ManagerView;
import com.rjgc.user.view.UserView;
import com.rjgc.util.DimensionUtil;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/29/21
 * @Time: 2:01 PM
 */
public class MainView extends JFrame {
    JSplitPane jSplitPane = new JSplitPane();
    // 设置左侧内容
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
    DefaultMutableTreeNode manager = new DefaultMutableTreeNode("管理员帐号管理");
    DefaultMutableTreeNode user = new DefaultMutableTreeNode("会员用户管理");
    DefaultMutableTreeNode coach = new DefaultMutableTreeNode("健身教练管理");
    DefaultMutableTreeNode equipment = new DefaultMutableTreeNode("健身器材管理");
    JTree jTree = new JTree(root);
    Color color = new Color(203, 220, 217);
//    ImageIcon imageIcon = new ImageIcon("login.png");
//    JPanel jPanel = new JPanel();
    JLabel jLabel = new JLabel();
//    Icon icon = new ImageIcon("/home/xinong/IdeaProjects/课程设计/src/main/resources/MainSystem.png");
    private String userName;
    int lastRow = 2;
    public MainView(){
        root.add(manager);
        root.add(user);
        root.add(coach);
        root.add(equipment);
        MyRender myRender = new MyRender();
        myRender.setBackgroundNonSelectionColor(color);
        myRender.setBackgroundSelectionColor(new Color(140, 140, 140));
        jTree.setCellRenderer(myRender);
        jTree.setBackground(color);
        expandAll(jTree, new TreePath(root), true);
//        jLabel.setIcon(icon);
//        jPanel.add(jLabel);
//        jPanel.setSize(800, 600);
        // 设置当前默认选中会员用户管理
        jTree.setSelectionRow(2);


        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            // 条目选中后，这个方法会执行
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // 得到当前选中的结点对象
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                if (manager.equals(lastPathComponent)){
                    if(!Objects.equals(userName, "admin")){
                        JOptionPane.showMessageDialog(jLabel,"权限不足，非admin用户不允许查看");
                        setRow();
                    }
                    else{
                        jSplitPane.setRightComponent(new ManagerView());
                    }
                }else if(user.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new UserView());
                    jSplitPane.setDividerLocation(150);
                }else if(coach.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new CoachView());
                }else if(equipment.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new EquipmentView());
                }
                lastRow = getRow(lastPathComponent);
            }

            private void setRow() {
                if(lastRow == 1){
                    jSplitPane.setRightComponent(new ManagerView());
                }else if(lastRow == 2){
                    jSplitPane.setRightComponent(new UserView());
                }
                else if(lastRow == 3){
                    jSplitPane.setRightComponent(new CoachView());
                }
                else if(lastRow == 4){
                    jSplitPane.setRightComponent(new EquipmentView());
                }
                jTree.setSelectionRow(lastRow);
            }

            private int  getRow(Object lastPathComponent) {
                int row = 0;
                if(manager.equals(lastPathComponent)){
                    row = 1;
                }else if(user.equals(lastPathComponent)){
                    row = 2;
                }else if(coach.equals(lastPathComponent)){
                    row = 3;
                }else if(equipment.equals(lastPathComponent)){
                    row = 4;
                }
                return row;
            }
        });

        jSplitPane.setContinuousLayout(true);
        jSplitPane.setDividerLocation(160);
        jSplitPane.setDividerSize(10);
        jSplitPane.setLeftComponent(jTree);
        jSplitPane.setRightComponent(new UserView());
        add(jSplitPane);
        // 根据屏幕设置主界面的大小
        setBounds(DimensionUtil.getBounds());
        // 设置窗体完全充满整个屏幕的可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }
    private void expandAll(JTree tree, TreePath parent, boolean expand) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() > 0) {
            for (Enumeration<? extends TreeNode> e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }
        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
