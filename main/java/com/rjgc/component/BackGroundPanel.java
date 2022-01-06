package com.rjgc.component;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/29/21
 * @Time: 4:35 PM
 */
public class BackGroundPanel extends JPanel {
    ImageIcon imageIcon;
    Image image;
//    public BackGroundPanel(Image backIcon){
//        this.backIcon = backIcon;
//    }
    public BackGroundPanel(){
        imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/home/xinong/IdeaProjects/课程设计/src/main/resources/login.png")));
        image = imageIcon.getImage();
    }

    @Override
    public void paint(Graphics g) {
        // 绘制背景
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 1600, 900, this);
    }
}
