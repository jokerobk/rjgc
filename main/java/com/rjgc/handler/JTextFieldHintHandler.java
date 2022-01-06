package com.rjgc.handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/29/21
 * @Time: 12:40 AM
 */
public class JTextFieldHintHandler implements FocusListener {
    private String hintText;
    private JTextField textField;
    public JTextFieldHintHandler(JTextField jTextField, String hintText) {
        this.textField = jTextField;
        this.hintText = hintText;
        jTextField.setText(hintText);  //默认直接显示
        jTextField.setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        //获取焦点时，清空提示内容
        String temp = textField.getText();
        if(temp.equals(hintText)) {
            textField.setText("");
            textField.setForeground(Color.BLACK);
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        //失去焦点时，没有输入内容，显示提示内容
        String temp = textField.getText();
        if(temp.equals("")) {
            textField.setForeground(Color.GRAY);
            textField.setText(hintText);
        }

    }

}

