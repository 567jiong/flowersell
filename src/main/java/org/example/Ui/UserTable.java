package org.example.Ui;

import org.example.POJO.Flower;
import org.example.POJO.Flowerstore;
import org.example.POJO.Orders;
import org.example.Service.FlowerService;
import org.example.Service.FlowerstoreService;
import org.example.Service.Impl.FlowerServiceImpl;
import org.example.Service.Impl.FlowerstoreServiceImpl;
import org.example.Service.Impl.OrdersServiceImpl;
import org.example.Service.OrdersService;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class UserTable extends JFrame implements ActionListener {
    FlowerstoreService flowerstoreService= new FlowerstoreServiceImpl();
    OrdersService ordersService = new OrdersServiceImpl();
    FlowerService flowerService = new FlowerServiceImpl();
    private static Vector<String> vNames=new Vector<String>();
    private static Vector<String> vOrderNames= new Vector<String>();
    static {
        vNames.add("鲜花名称");
        vNames.add("鲜花价格");
        vOrderNames.add("购买人");
        vOrderNames.add("购买鲜花名");
        vOrderNames.add("购买数量");
        vOrderNames.add("总价格");
        vOrderNames.add("购买时间");
    }
    Vector vdata = new Vector();
    Vector vorderdata= new Vector();
    DefaultTableModel ft;
    private JPanel Panel;
    private JTextField textField;
    private JButton searchButton;
    private JButton orderButton;
    private JTable table=new JTable();

    private JTextField flowerField;
    private JLabel numLabel;
    private JTextField numField;
    private JButton buyButton;

    public UserTable(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null); //设置窗口居中显示
        setResizable(false); // 不可以调窗口大小
        init();
    }

    private void init() {
        Panel=new JPanel();
        Panel.setLayout(null);
        textField=new JTextField();
        textField.setBounds(100,20,150,30);
        searchButton=new JButton("搜索");
        searchButton.setBounds(260,20,60,30);
        searchButton.addActionListener(this);
        orderButton=new JButton("查看我的订单");
        orderButton.setBounds(360,20,140,30);
        orderButton.addActionListener(this);

        Panel.add(textField);
        Panel.add(searchButton);
        Panel.add(orderButton);

        // 数据居中显示
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dc);
        List<Flowerstore> allFlower = flowerstoreService.findAllFlower();
        for (Flowerstore flowerstore : allFlower) {
            Vector v = new Vector();
            v.add(flowerstore.getFlowerName());
            v.add(flowerstore.getOutPrice());
            vdata.add(v);
        }
        ft= new DefaultTableModel(vdata,vNames);
        table.setModel(ft);
        table.setBounds(100,70,400,350);
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setBounds(100,70,400,350);
        Panel.add(scrollpane);

        JLabel flowerLabel=new JLabel("购买鲜花名称:");
        flowerLabel.setBounds(60,450,100,30);
        Panel.add(flowerLabel);
        flowerField=new JTextField();
        flowerField.setBounds(150,455,70,25);
        Panel.add(flowerField);
        numLabel=new JLabel("购买鲜花数量:");
        numLabel.setBounds(240,450,100,30);
        Panel.add(numLabel);
        numField=new JTextField();
        numField.setBounds(340,455,70,25);
        Panel.add(numField);
        buyButton=new JButton("购买鲜花");
        buyButton.setBounds(440,450,120,30);
        buyButton.addActionListener(this);
        Panel.add(buyButton);


        this.add(Panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==searchButton){
            if(textField.getText()!=null && textField.getText().length()>0){
                Flowerstore flower = flowerstoreService.findFlowerByName(textField.getText());
                if(flower==null) return ;
                Vector vdataflower = new Vector();
                Vector v1=new Vector();
                v1.add(flower.getFlowerName());
                v1.add(flower.getOutPrice());
                vdataflower.add(v1);
                ft= new DefaultTableModel(vdataflower,vNames);
                table.setModel(ft);
            }else{
                ft= new DefaultTableModel(vdata,vNames);
                table.setModel(ft);
            }
        }
        if(e.getSource()==orderButton){
            vorderdata.clear();
            List<Orders> orders = ordersService.findOrders(LoginTable.LoginUserName);
            if(orders == null || orders.size()==0) return;
            for (Orders order : orders) {
                Vector v= new Vector();
                v.add(order.getBuyUserName());
                v.add(order.getBuyFlowerName());
                v.add(order.getBuyNumber());
                v.add(order.getTotalPrice());
                v.add(order.getBuyTime());
                vorderdata.add(v);
            }
            ft=new DefaultTableModel(vorderdata,vOrderNames);
            table.setModel(ft);
        }
        if(e.getSource()==buyButton){
            if(numField.getText()==null ||numField.getText().length()==0||flowerField.getText().length()==0|| flowerField.getText()==null){
                JOptionPane.showMessageDialog(null, "请输入购买鲜花的名称和数量!", "提示", JOptionPane. ERROR_MESSAGE);
                return;
            }
            String flowerName=flowerField.getText();
            long num=Long.valueOf(numField.getText());
            long flowerNumber = flowerService.findFlowerNumber(flowerName);
            Flowerstore flowerByName = flowerstoreService.findFlowerByName(flowerName);
            if(flowerByName==null){
                JOptionPane.showMessageDialog(null, "购买失败！\n"+flowerName+"鲜花未上架", "提示", JOptionPane. ERROR_MESSAGE);
                return;
            }
            if(flowerNumber<num){
                JOptionPane.showMessageDialog(null, "购买失败！\n"+flowerName+"鲜花的库存不足"+num+"个！", "提示", JOptionPane. ERROR_MESSAGE);
                return;
            }
            flowerService.reduceFlowerNumber(flowerName,num);
            double money = flowerstoreService.buyFlower(flowerName, num);
            JOptionPane.showMessageDialog(null, "购买成功！\n您所需支付的金额为:"+money,"提示",JOptionPane.INFORMATION_MESSAGE);
            flowerField.setText("");
            numField.setText("");
        }
    }
}
