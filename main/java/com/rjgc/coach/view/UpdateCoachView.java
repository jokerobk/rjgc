package com.rjgc.coach.view;

import com.rjgc.Service.CoachService;
import com.rjgc.Service.impl.CoachServiceImpl;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.entity.CoachDO;
import com.rjgc.entity.UserDO;
import com.rjgc.handler.CoachHandler.UpdateCoachInfoHandler;
import com.rjgc.handler.UserHandler.UpdateUserInfoHandler;
import com.rjgc.user.view.UserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;
import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/31/21
 * @Time: 10:53 AM
 */
public class UpdateCoachView extends JFrame {
    JLabel coachIdJLabel = new JLabel("教练编号: ", JLabel.RIGHT);
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    JLabel nameLabel = new JLabel("姓名:", JLabel.RIGHT);
    JTextField nameTextField = new JTextField();
    JLabel sexLabel = new JLabel("性别:", JLabel.RIGHT);
    JTextField sexTextField = new JTextField();
    JLabel numLabel = new JLabel("联系方式:", JLabel.RIGHT);
    JTextField numTextField = new JTextField();
    JLabel dateLabel = new JLabel("加入日期:", JLabel.RIGHT);
    JTextField dateTextField = new JTextField();
    JLabel extendLabel = new JLabel("备注:", JLabel.RIGHT);
    JTextField extendTextField = new JTextField();
    JButton updateButton = new JButton("提交");
    JComboBox jComboBox = new JComboBox();
    Vector<String> coachId = new Vector<>();
    Vector<Object> coachInfo;
    CoachService coachService = new CoachServiceImpl();

    String name;
    String sex;
    String num;
    String date;
    String extend;
    UpdateCoachInfoHandler updateCoachInfoHandler;
    public UpdateCoachView(CoachView coachView){
        setTitle("修改教练信息");
        updateCoachInfoHandler = new UpdateCoachInfoHandler(this, coachView);
        coachId = coachService.getCoachId();
        jComboBox.addItem("--请选择--");
        for(String id : coachId){
            jComboBox.addItem(id);
        }
        // 设置监听
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {

                    nameTextField.setText("");
                    sexTextField.setText("");
                    numTextField.setText("");
                    dateTextField.setText("");
                    extendTextField.setText("");

                    if (!Objects.equals(e.getItem(), "--请选择--")) {
                        coachInfo = coachService.getCoachInfo(e.getItem());
                        name = (String) coachInfo.get(0);
                        sex = (String) coachInfo.get(1);
                        num = (String) coachInfo.get(2);
                        date = (String) coachInfo.get(3);
                        extend = (String) coachInfo.get(4);
                        nameTextField.setText(name);
                        sexTextField.setText(sex);
                        numTextField.setText(num);
                        dateTextField.setText(date);
                        extendTextField.setText(extend);

                    }
                }
            }
        });
        coachIdJLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(coachIdJLabel);
        jComboBox.setPreferredSize(new Dimension(200, 30));
        jPanel.add(jComboBox);
        nameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(nameLabel);
        nameTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(nameTextField);

        sexLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(sexLabel);
        sexTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(sexTextField);

        numLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(numLabel);
        numTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(numTextField);


        dateLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(dateLabel);
        dateTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(dateTextField);


        extendLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(extendLabel);
        extendTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(extendTextField);

        jPanel.add(updateButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        updateButton.addActionListener(updateCoachInfoHandler);
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public CoachDO updateCoachDO(){
        CoachDO coachDO = new CoachDO();

        coachDO.setId(Integer.parseInt(Objects.requireNonNull(jComboBox.getSelectedItem()).toString().trim()));
        coachDO.setName(nameTextField.getText());
        coachDO.setSex(sexTextField.getText());
        coachDO.setPhoneNum(numTextField.getText());
        coachDO.setDate(dateTextField.getText());
        coachDO.setExtend(extendTextField.getText());
        return coachDO;
    }
}
