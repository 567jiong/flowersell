package org.example.Ui;

import org.example.Service.CustomerService;
import org.example.Service.Impl.CustomerServiceImpl;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginTable extends JFrame implements ActionListener {

    public static String LoginUserName ;
    private CustomerService customerService = new CustomerServiceImpl();
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton login;
    private JButton register;

    public LoginTable(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        login = new JButton("登录");
        login.setBounds(60, 100, 80, 25);
        Panel.add(login);

        register = new JButton("注册");
        register.setBounds(180, 100, 80, 25);
        Panel.add(register);

        login.addActionListener(this);
        register.addActionListener(this);
        this.add(Panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            String UserName = textField.getText();
            String passWord= new String(passwordField.getPassword());
            if(UserName.equals("admin") && passWord.equals("123")){
                JOptionPane.showMessageDialog(null, "管理员登录成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                dispose();
                AdminTable adminTable = new AdminTable();
                adminTable.setTitle("鲜花销售系统管理员界面");
                adminTable.setVisible(true);
                return ;
            }
            int p=customerService.login(UserName,passWord);
            if(p==1){
                LoginUserName=UserName;
                JOptionPane.showMessageDialog(null, "用户登录成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                dispose();
                UserTable userTable =new UserTable();
                userTable.setTitle("鲜花销售系统");
                userTable.setVisible(true);
            }else if(p==2){
                JOptionPane.showMessageDialog(null, "账号不存在,请注册!", "提示", JOptionPane. ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "密码错误!", "提示", JOptionPane. ERROR_MESSAGE);
            }
        }
        if(e.getSource()==register){
            registerTable registerTable= new registerTable();
            registerTable.setTitle("鲜花销售系统注册页面");
            registerTable.setVisible(true);
        }
    }
}
