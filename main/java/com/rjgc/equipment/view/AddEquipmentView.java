package com.rjgc.equipment.view;

import com.rjgc.Service.EquipmentService;

import com.rjgc.Service.impl.EquipmentServiceImpl;

import com.rjgc.entity.EquipmentDO;

import com.rjgc.handler.EquipmentHanler.AddEquipmentInfoHandler;

import com.rjgc.handler.JTextFieldHintHandler;
import com.rjgc.util.RandomNumUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: zhangxiaofeng
 * Date: 2021/12/31
 * Time: 18:51
 */
public class AddEquipmentView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    JLabel idLabel = new JLabel("器材编号:", JLabel.RIGHT);
    JTextField idTextField = new JTextField();
    JLabel nameLabel = new JLabel("器材名称:", JLabel.RIGHT);
    JTextField nameTextField = new JTextField();
    JLabel dateLabel = new JLabel("购买日期:", JLabel.RIGHT);
    JTextField dateTextField = new JTextField();
    JLabel companyLabel = new JLabel("购买商家:", JLabel.RIGHT);
    JTextField companyTextField = new JTextField();
    JLabel numLabel = new JLabel("数量:", JLabel.RIGHT);
    JTextField numTextField = new JTextField();
    JLabel priceLabel = new JLabel("价格(单价):", JLabel.RIGHT);
    JTextField priceTextField = new JTextField();
    JButton addButton = new JButton("提交");
    // 获取时间

    AddEquipmentInfoHandler addEquipmentInfoHandler;
    public AddEquipmentView(EquipmentView equipmentView){

        setTitle("添加器材");
        addEquipmentInfoHandler = new AddEquipmentInfoHandler(this, equipmentView);
        idLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(idLabel);
        idTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(idTextField);

        nameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(nameLabel);
        nameTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(nameTextField);

        dateTextField.addFocusListener(new JTextFieldHintHandler(dateTextField, "格式为yyyy-mm-dd"));
        dateLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(dateLabel);
        dateTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(dateTextField);

        companyLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(companyLabel);
        companyTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(companyTextField);


        numLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(numLabel);
        numTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(numTextField);

        priceLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(priceLabel);
        priceTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(priceTextField);


        jPanel.add(addButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        addButton.addActionListener(addEquipmentInfoHandler);
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public EquipmentDO addEquipmentDO(){
        EquipmentDO equipmentDO = new EquipmentDO();
        RandomNumUtil randomNumUtil = new RandomNumUtil();
        EquipmentService equipmentsService = new EquipmentServiceImpl();
        int totalPrice = Integer.parseInt(numTextField.getText()) * Integer.parseInt(priceTextField.getText());
        equipmentDO.setId(Integer.parseInt(idTextField.getText()));
        equipmentDO.setName(nameTextField.getText());
        equipmentDO.setDate(dateTextField.getText());
        equipmentDO.setCompany(companyTextField.getText());
        equipmentDO.setNum(Integer.parseInt(numTextField.getText()));
        equipmentDO.setPrice(Integer.parseInt(priceTextField.getText()));
        equipmentDO.setTotalPrice(totalPrice);
        return equipmentDO;
    }
}
