package org.example.Ui;

import org.example.Service.CustomerService;
import org.example.Service.Impl.CustomerServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerTable extends JFrame implements ActionListener {

    private CustomerService customerService = new CustomerServiceImpl();
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton register;
    public registerTable(){
        setSize(350, 200);
        setLocationRelativeTo(null); //设置窗口居中显示
        setResizable(false); // 不可以调窗口大小
        init();
    }

    private void init() {
        textField = new JTextField();
        textField.setBounds(100, 20, 165, 25);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 50, 165, 25);

        JPanel Panel = new JPanel();
        Panel.setLayout(null);

        JLabel labelLogin = new JLabel("用户名:");
        labelLogin.setBounds(20, 20, 70, 25);
        Panel.add(labelLogin);
        Panel.add(textField);

        JLabel labelregister = new JLabel("密码:");
        Panel.add(labelregister);
        labelregister.setBounds(20, 50, 70, 25);
        Panel.add(passwordField);


        register = new JButton("注册");
        register.setBounds(120, 100, 100, 40);
        Panel.add(register);

        register.addActionListener(this);
        this.add(Panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==register){
            String UserName = textField.getText();
            String passWord = new String(passwordField.getPassword());
            if(customerService.findUserName(UserName)){
                JOptionPane.showMessageDialog(null, "用户名已存在,请重新输入", "提示", JOptionPane. ERROR_MESSAGE);
            } else if (passWord==null || UserName==null || passWord.length()==0 || UserName.length()==0) {
                JOptionPane.showMessageDialog(null, "用户名或密码不能为空", "提示", JOptionPane. ERROR_MESSAGE);
            } else{
                customerService.addCustomer(UserName,passWord);
                JOptionPane.showMessageDialog(null, "注册成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }
}
