package com.rjgc.user.view;

import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.entity.UserDO;
import com.rjgc.handler.UserHandler.AddUserInfoHandler;
import com.rjgc.util.RandomNumUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 11:51 PM
 */
public class AddUserView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    JLabel nameLabel = new JLabel("姓名:", JLabel.RIGHT);
    JTextField nameTextField = new JTextField();
    JLabel sexLabel = new JLabel("性别:", JLabel.RIGHT);
    JTextField sexTextField = new JTextField();
    JLabel numLabel = new JLabel("联系方式:", JLabel.RIGHT);
    JTextField numTextField = new JTextField();
    JLabel dateLabel = new JLabel("日期:", JLabel.RIGHT);
    JTextField dateTextField = new JTextField();
    JLabel levelLabel = new JLabel("会员等级:", JLabel.RIGHT);
    JTextField levelTextField = new JTextField();
    JLabel dayLabel = new JLabel("会员时长:", JLabel.RIGHT);
    JTextField dayTextField = new JTextField();
    JLabel coachIdLabel = new JLabel("教练编号:", JLabel.RIGHT);
    JTextField coachIdTextField = new JTextField();
    JLabel extendLabel = new JLabel("备注:", JLabel.RIGHT);
    JTextField extendTextField = new JTextField();
    JButton addButton = new JButton("提交");
    // 获取时间
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    AddUserInfoHandler addUserInfoHandler;
    public AddUserView(UserView userView){
//        super(userView, "添加会员", true);

        setTitle("添加会员用户");
        addUserInfoHandler = new AddUserInfoHandler(this, userView);
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

//        dateTextField.addFocusListener(new JTextFieldHintHandler(dateTextField, "格式为yyyy-mm-dd"));
//        dateLabel.setPreferredSize(new Dimension(80, 30));
//        jPanel.add(dateLabel);
//        dateTextField.setPreferredSize(new Dimension(200, 30));
//        jPanel.add(dateTextField);

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

        jPanel.add(addButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        addButton.addActionListener(addUserInfoHandler);
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public UserDO addUserDO(){
        UserDO userDO = new UserDO();
        RandomNumUtil randomNumUtil = new RandomNumUtil();
        UsersService usersService = new UserServiceImpl();
        boolean idExist = true;
        while(idExist){
            int id = randomNumUtil.gerRandomNum();
            idExist = usersService.judgeId(id);
            if(!idExist){
                userDO.setId(id);
            }
        }
        userDO.setName(nameTextField.getText());
        userDO.setSex(sexTextField.getText());
        userDO.setPhoneNum(numTextField.getText());
        userDO.setDays(dayTextField.getText());
        userDO.setDate(df.format(new Date()));
        userDO.setLevel(levelTextField.getText());
        userDO.setCoachId((Integer.valueOf(coachIdTextField.getText())));
        userDO.setExtend(extendTextField.getText());
        return userDO;
    }
}
