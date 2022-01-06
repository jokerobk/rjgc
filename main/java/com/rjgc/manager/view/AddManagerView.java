package com.rjgc.manager.view;

import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.entity.ManagerDO;
import com.rjgc.entity.UserDO;
import com.rjgc.handler.ManagerHandler.AddManagerInfoHandler;
import com.rjgc.handler.UserHandler.AddUserInfoHandler;
import com.rjgc.util.RandomNumUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/30/21
 * @Time: 10:17 PM
 */
public class AddManagerView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    JLabel managerNameLabel = new JLabel("管理员帐号:", JLabel.RIGHT);
    JTextField managerNameTextField = new JTextField();
    JLabel managerPwdLabel = new JLabel("密码:", JLabel.RIGHT);
    JTextField managerPwdTextField = new JTextField();
    JLabel holderIdLabel = new JLabel("拥用者编号:", JLabel.RIGHT);
    JTextField holderIdTextField = new JTextField();
    JLabel holderNameLabel = new JLabel("拥用者姓名:", JLabel.RIGHT);
    JTextField holderNameTextField = new JTextField();
    JButton addButton = new JButton("提交");
    private AddManagerInfoHandler addManagerInfoHandler;

    public AddManagerView(ManagerView managerView){
        setTitle("添加管理员账户");
        addManagerInfoHandler = new AddManagerInfoHandler(this, managerView);
        managerNameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(managerNameLabel);
        managerNameTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(managerNameTextField);

        managerPwdLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(managerPwdLabel);
        managerPwdTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(managerPwdTextField);

        holderIdLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(holderIdLabel);
        holderIdTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(holderIdTextField);


        holderNameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(holderNameLabel);
        holderNameTextField.setPreferredSize(new Dimension(200, 30));
        jPanel.add(holderNameTextField);



        jPanel.add(addButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        addButton.addActionListener(addManagerInfoHandler);
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public ManagerDO addManagerDO(){
        ManagerDO managerDO = new ManagerDO();
//        RandomNumUtil randomNumUtil = new RandomNumUtil();
//        UsersService usersService = new UserServiceImpl();
//        boolean idExist = true;
//        while(idExist){
//            int id = randomNumUtil.gerRandomNum();
//            idExist = usersService.judgeId(id);
//            if(!idExist){
//                userDO.setId(id);
//            }
//        }
        managerDO.setManagerName(managerNameTextField.getText());
        managerDO.setManagerPwd(managerPwdTextField.getText());
        managerDO.setHolderId(Integer.parseInt(holderIdTextField.getText()));
        managerDO.setHolderName(holderNameTextField.getText());
        return managerDO;
    }
}
