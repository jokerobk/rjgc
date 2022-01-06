package com.rjgc.coach.view;

import com.rjgc.Service.CoachService;
import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.CoachServiceImpl;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.entity.CoachDO;
import com.rjgc.entity.UserDO;
import com.rjgc.handler.CoachHandler.AddCoachInfoHandler;
import com.rjgc.handler.JTextFieldHintHandler;
import com.rjgc.handler.UserHandler.AddUserInfoHandler;
import com.rjgc.util.RandomNumUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/31/21
 * @Time: 10:52 AM
 */
public class AddCoachView extends JDialog {
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
    JButton addButton = new JButton("提交");
    // 获取时间
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    AddCoachInfoHandler addCoachInfoHandler;
    public AddCoachView (CoachView coachView){
        setTitle("添加教练");
        addCoachInfoHandler = new AddCoachInfoHandler(this, coachView);

        // 设置姓名
        nameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(nameLabel);
        nameTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(nameTextField);
        // 设置性别
        sexLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(sexLabel);
        sexTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(sexTextField);
        // 设置联系方式
        numLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(numLabel);
        numTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(numTextField);
        // 设置加入日期
        dateTextField.addFocusListener(new JTextFieldHintHandler(dateTextField, "格式为yyyy-mm-dd"));
        dateLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(dateLabel);
        dateTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(dateTextField);

        // 设置备注
        extendLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(extendLabel);
        extendTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(extendTextField);

        jPanel.add(addButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        addButton.addActionListener(addCoachInfoHandler);
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public CoachDO addCoachDO(){
        CoachDO coachDO = new CoachDO();
        RandomNumUtil randomNumUtil = new RandomNumUtil();
        CoachService coachService = new CoachServiceImpl();
        boolean idExist = true;
        while(idExist){
            int id = randomNumUtil.gerRandomNum();
            idExist = coachService.judgeId(id);
            if(!idExist){
                coachDO.setId(id);
            }
        }
        coachDO.setName(nameTextField.getText());
        coachDO.setSex(sexTextField.getText());
        coachDO.setPhoneNum(numTextField.getText());
        coachDO.setDate(dateTextField.getText());
        coachDO.setExtend(extendTextField.getText());
        return coachDO;
    }
}
