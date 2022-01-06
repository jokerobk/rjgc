package com.rjgc.component;

import com.rjgc.util.PathUtil;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/29/21
 * @Time: 4:05 PM
 * @features: 绘制树结点图标
 */
public class MyRender extends DefaultTreeCellRenderer {
    private ImageIcon rootIcon = null;
    private ImageIcon managerIcon = null;
    private ImageIcon userIcon = null;
    private ImageIcon coachIcon = null;
    private ImageIcon equipmentIcon = null;

    public MyRender(){
        rootIcon = new ImageIcon(PathUtil.getRealPath("system.png"));
        managerIcon = new ImageIcon(PathUtil.getRealPath("manager.png"));
        userIcon = new ImageIcon(PathUtil.getRealPath("user.png"));
        coachIcon = new ImageIcon(PathUtil.getRealPath("coach.png"));
        equipmentIcon = new ImageIcon(PathUtil.getRealPath("equipment.png"));
    }
    // 当绘制树的每个结点时，都会调用这个方法
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        ImageIcon image = null;

        switch(row) {
            case 0:
                image = rootIcon;
                break;
            case 1:
                image = managerIcon;
                break;
            case 2:
                image = userIcon;
                break;
            case 3:
                image = coachIcon;
                break;
            case 4:
                image = equipmentIcon;
                break;
        }
        this.setIcon(image);
        return this;
    }
}
