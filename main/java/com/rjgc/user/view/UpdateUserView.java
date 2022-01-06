package com.rjgc.user.view;

import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.entity.UserDO;
import com.rjgc.handler.UserHandler.UpdateUserInfoHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;
import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/29/21
 * @Time: 4:14 PM
 */
public class UpdateUserView extends JFrame {

    JLabel userIdJLabel = new JLabel("会员用户编号: ", JLabel.RIGHT);
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    JLabel nameLabel = new JLabel("姓名:", JLabel.RIGHT);
    JTextField nameTextField = new JTextField();
    JLabel sexLabel = new JLabel("性别:", JLabel.RIGHT);
    JTextField sexTextField = new JTextField();
    JLabel numLabel = new JLabel("联系方式:", JLabel.RIGHT);
    JTextField numTextField = new JTextField();
    JLabel levelLabel = new JLabel("会员等级:", JLabel.RIGHT);
    JTextField levelTextField = new JTextField();
    JLabel dayLabel = new JLabel("会员时长:", JLabel.RIGHT);
    JTextField dayTextField = new JTextField();
    JLabel coachIdLabel = new JLabel("教练编号:", JLabel.RIGHT);
    JTextField coachIdTextField = new JTextField();
    JLabel extendLabel = new JLabel("备注:", JLabel.RIGHT);
    JTextField extendTextField = new JTextField();
    JButton updateButton = new JButton("提交");
    JComboBox jComboBox = new JComboBox();
    Vector<String> userId = new Vector<>();
    Vector<Object> userInfo;
    UserServiceImpl userService = new UserServiceImpl();

    String name;
    String sex;
    String num;
    String level;
    String day;
    String coachId;
    String extend;
    UpdateUserInfoHandler updateUserInfoHandler;
    public UpdateUserView(UserView userView){
        setTitle("修改会员用户信息");
        updateUserInfoHandler = new UpdateUserInfoHandler(this, userView);
        userId = userService.getUserId();
        jComboBox.addItem("--请选择--");
        for(String id : userId){
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
                    levelTextField.setText("");
                    dayTextField.setText("");
                    coachIdTextField.setText("");
                    extendTextField.setText("");

                    if (!Objects.equals((String) e.getItem(), "--请选择--")) {
                        userInfo = userService.getUserInfo(e.getItem());
                        name = (String) userInfo.get(0);
                        sex = (String) userInfo.get(1);
                        num = (String) userInfo.get(2);
                        level = (String) userInfo.get(3);
                        day = (String) userInfo.get(4);
                        coachId = (String) userInfo.get(5);
                        extend = (String) userInfo.get(6);
                        nameTextField.setText(name);
                        sexTextField.setText(sex);
                        numTextField.setText(num);
                        levelTextField.setText(level);
                        dayTextField.setText(day);
                        coachIdTextField.setText(coachId);
                        extendTextField.setText(extend);

                    }
                }
            }
        });
        userIdJLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(userIdJLabel);
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


        levelLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(levelLabel);
        levelTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(levelTextField);

        dayLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(dayLabel);
        dayTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(dayTextField);

        coachIdLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(coachIdLabel);
        coachIdTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(coachIdTextField);

        extendLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(extendLabel);
        extendTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(extendTextField);

        jPanel.add(updateButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        updateButton.addActionListener(updateUserInfoHandler);
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public UserDO updateUserDO(){
        UserDO userDO = new UserDO();

        userDO.setId(Integer.valueOf(Objects.requireNonNull(jComboBox.getSelectedItem()).toString().trim()));
        userDO.setName(nameTextField.getText());
        userDO.setSex(sexTextField.getText());
        userDO.setPhoneNum(numTextField.getText());
        userDO.setDays(dayTextField.getText());
        userDO.setLevel(levelTextField.getText());
        userDO.setCoachId((Integer.valueOf(coachIdTextField.getText())));
        userDO.setExtend(extendTextField.getText());
        return userDO;
    }

}
