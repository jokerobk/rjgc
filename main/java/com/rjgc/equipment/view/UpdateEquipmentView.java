package com.rjgc.equipment.view;

import com.rjgc.Service.impl.EquipmentServiceImpl;

import com.rjgc.entity.EquipmentDO;

import com.rjgc.handler.EquipmentHanler.UpdateEquipmentInfoHandler;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;
import java.util.Vector;

/**
 * Author: zhangxiaofeng
 * Date: 2021/12/31
 * Time: 18:51
 */
public class UpdateEquipmentView extends JFrame {
    JLabel idJLabel = new JLabel("器材编号: ", JLabel.RIGHT);
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
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
    JLabel totalPriceLabel = new JLabel("总价:", JLabel.RIGHT);
    JTextField totalPriceTextField = new JTextField();
    JButton updateButton = new JButton("提交");
    JComboBox jComboBox = new JComboBox();
    Vector<String> equipmentId = new Vector<>();
    Vector<Object> equipmentInfo;
    EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();

    String name;
    String date;
    String company;
    int num;
    int price;
    int totalPrice;
    UpdateEquipmentInfoHandler updateEquipmentInfoHandler;
    public UpdateEquipmentView(EquipmentView equipmentView){
        setTitle("修改器材信息");
        totalPriceLabel.setVisible(false);
        totalPriceTextField.setVisible(false);
        updateEquipmentInfoHandler = new UpdateEquipmentInfoHandler(this, equipmentView);
        equipmentId = equipmentService.getEquipmentId();
        jComboBox.addItem("--请选择--");
        for(String id : equipmentId){
            jComboBox.addItem(id);
        }
        // 设置监听
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {


                    nameTextField.setText("");
                    dateTextField.setText("");
                    companyTextField.setText("");
                    numTextField.setText("");
                    priceTextField.setText("");

                    if (!Objects.equals((String) e.getItem(), "--请选择--")) {
                        equipmentInfo = equipmentService.getEquipmentInfo(e.getItem());

                        name = (String) equipmentInfo.get(0);
                        date = (String) equipmentInfo.get(1);
                        company = (String) equipmentInfo.get(2);
                        num = (int) equipmentInfo.get(3);
                        price = (int) equipmentInfo.get(4);
                        totalPrice = num * price;
                        nameTextField.setText(name);
                        dateTextField.setText(date);
                        companyTextField.setText(company);
                        numTextField.setText(String.valueOf(num));
                        priceTextField.setText(String.valueOf(price));

                    }
                }
            }
        });
        idJLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(idJLabel);
        jComboBox.setPreferredSize(new Dimension(200, 30));
        jPanel.add(jComboBox);
        nameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(nameLabel);
        nameTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(nameTextField);

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

        totalPriceLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(totalPriceLabel);
        totalPriceTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(totalPriceTextField);


        jPanel.add(updateButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        updateButton.addActionListener(updateEquipmentInfoHandler);
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public EquipmentDO updateEquipmentDO(){
        EquipmentDO equipmentDO = new EquipmentDO();

        equipmentDO.setId(Integer.valueOf(Objects.requireNonNull(jComboBox.getSelectedItem()).toString().trim()));
        equipmentDO.setName(nameTextField.getText());
        equipmentDO.setDate(dateTextField.getText());
        equipmentDO.setCompany(companyTextField.getText());
        equipmentDO.setNum(Integer.parseInt(numTextField.getText()));
        equipmentDO.setPrice(Integer.parseInt(priceTextField.getText()));
        int totalPrice = Integer.parseInt(numTextField.getText()) * Integer.parseInt(priceTextField.getText());
        equipmentDO.setTotalPrice(totalPrice);
        return equipmentDO;
    }
}
