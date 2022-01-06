package com.rjgc.manager.view;

import com.rjgc.Service.ManagerService;
import com.rjgc.Service.impl.ManagerServiceImpl;

import com.rjgc.entity.ManagerDO;

import com.rjgc.handler.ManagerHandler.UpdateManagerInfoHandler;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;
import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/30/21
 * @Time: 10:17 PM
 */
public class UpdateManagerView extends JFrame {
    JLabel managerIdJLabel = new JLabel("管理员编号编号: ", JLabel.RIGHT);
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    JLabel managerNameLabel = new JLabel("管理员帐号:", JLabel.RIGHT);
    JTextField managerNameTextField = new JTextField();
    JLabel managerPwdLabel = new JLabel("密码:", JLabel.RIGHT);
    JTextField managerPwdTextField = new JTextField();
    JLabel holderIdLabel = new JLabel("拥用者编号:", JLabel.RIGHT);
    JTextField holderIdTextField = new JTextField();
    JLabel holderNameLabel = new JLabel("拥用者姓名:", JLabel.RIGHT);
    JTextField holderNameTextField = new JTextField();
    JButton updateButton = new JButton("提交");
    JComboBox jComboBox = new JComboBox();
    Vector<String> managerId = new Vector<>();
    Vector<Object> managerInfo;
    ManagerService managerService = new ManagerServiceImpl();

    String managerName;
    String managerPwd;
    int holderId;
    String holderName;
    UpdateManagerInfoHandler updateManagerInfoHandler;
    public UpdateManagerView(ManagerView managerView){
        setTitle("修改管理员账户信息");
        updateManagerInfoHandler = new UpdateManagerInfoHandler(this, managerView);
        managerId = managerService.getManagerId();
        jComboBox.addItem("--请选择--");
        for(String id : managerId){
            jComboBox.addItem(id);
        }
        // 设置监听
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {


                    managerNameTextField.setText("");
                    managerPwdTextField.setText("");
                    holderIdTextField.setText("");
                    holderNameTextField.setText("");

                    if (!Objects.equals(e.getItem(), "--请选择--")) {
                        managerInfo = managerService.getManagerInfo(e.getItem());
                        managerName = (String) managerInfo.get(0);
                        managerPwd = (String) managerInfo.get(1);
                        holderId = (Integer) managerInfo.get(2);
                        holderName = (String) managerInfo.get(3);
                        managerNameTextField.setText(managerName);
                        managerPwdTextField.setText(managerPwd);
                        holderIdTextField.setText(String.valueOf(holderId));
                        holderNameTextField.setText(holderName);

                    }
                }
            }
        });
        managerIdJLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(managerIdJLabel);
        jComboBox.setPreferredSize(new Dimension(200, 30));
        jPanel.add(jComboBox);
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

        jPanel.add(updateButton);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        updateButton.addActionListener(updateManagerInfoHandler);
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public ManagerDO updateManagerDO(){
        ManagerDO managerDO = new ManagerDO();
        managerDO.setId(Integer.parseInt(Objects.requireNonNull(jComboBox.getSelectedItem()).toString().trim()));
        managerDO.setManagerName(managerNameTextField.getText());
        managerDO.setManagerPwd(managerPwdTextField.getText());
        managerDO.setHolderId(Integer.parseInt(holderIdTextField.getText()));
        managerDO.setHolderName(holderNameTextField.getText());
        return managerDO;
    }

}
