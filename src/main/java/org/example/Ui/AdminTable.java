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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class AdminTable extends JFrame implements ActionListener {

    private FlowerService flowerService = new FlowerServiceImpl();
    private FlowerstoreService flowerstoreService= new FlowerstoreServiceImpl();
    private OrdersService ordersService = new OrdersServiceImpl();
    private JPanel Panel;
    private JTable table= new JTable();
    private JLabel flowerLabel;
    private JComboBox<String> comboBox ;
    private static JComboBox<String> updateBox;
    private  static Vector<String> vNames = new Vector<String>();
    private  static Vector<String> vOrderNames = new Vector<String>();
    static {
        updateBox= new JComboBox<String>();
        updateBox.addItem("售价");
        updateBox.addItem("库存");
        vNames.add("鲜花名称");
        vNames.add("鲜花进价");
        vNames.add("鲜花售价");
        vNames.add("鲜花库存");
        vNames.add("鲜花销售情况");
        vOrderNames.add("购买人");
        vOrderNames.add("购买鲜花名");
        vOrderNames.add("购买数量");
        vOrderNames.add("总价格");
        vOrderNames.add("购买时间");
    }
    DefaultTableModel ft;
    private JLabel updateLabel;
    private JLabel numLabel;
    private JTextField numField;
    private JButton updatebutton;

    private JLabel addflowerLabel;
    private JLabel addinPriceLable;
    private JLabel addoutPriceLable;
    private JLabel addnumberLable;
    private JButton addButton;
    private JTextField addflowerfield;
    private JTextField addinPricefield;
    private JTextField addoutPricefield;
    private JTextField addnumberfield;
    private JButton findAllOrders;
    private JButton findAllflwoers;
    public AdminTable(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null); //设置窗口居中显示
        setResizable(false); // 不可以调窗口大小
        init();
    }

    private void init() {
        Panel = new JPanel();
        Panel.setLayout(null);
        flowerLabel = new JLabel("修改鲜花名称:");
        flowerLabel.setBounds(30,10,90,30);
        Panel.add(flowerLabel);
        List<Flower> allFlower = flowerService.findAllFlower();
        comboBox= new JComboBox<String>();
        for (Flower flower : allFlower) {
            comboBox.addItem(flower.getName());
        }
        comboBox.setBounds(120,10,80,30);
        Panel.add(comboBox);

        updateLabel= new JLabel("修改功能:");
        updateLabel.setBounds(210,10,60,30);
        Panel.add(updateLabel);

        updateBox.setBounds(270,10,80,30);
        Panel.add(updateBox);


        numLabel = new JLabel("修改库存/价格:");
        numLabel.setBounds(360,10,90,30);
        Panel.add(numLabel);

        numField= new JTextField();
        numField.setBounds(450,10,60,30);
        Panel.add(numField);

        updatebutton= new JButton("修改");
        updatebutton.setBounds(510,10,70,30);
        updatebutton.addActionListener(this);
        Panel.add(updatebutton);

        Vector vdate = new Vector();
        List<Flower> flowers=flowerService.findAllFlower();
        List<Flowerstore> allFlower1 = flowerstoreService.findAllFlower();
        for(int i=0;i<flowers.size();i++){
            Vector v = new Vector();
            Flower flower = flowers.get(i);
            Flowerstore flowerstore = allFlower1.get(i);
            v.add(flower.getName());
            v.add(flower.getInPrice());
            v.add(flowerstore.getOutPrice());
            v.add(flower.getTotalNumber());
            v.add(flower.getBuyNumber());
            vdate.add(v);
        }
        ft= new DefaultTableModel(vdate,vNames);
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setBounds(50,60,480,350);
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dc);
        table.setModel(ft);
        table.setBounds(50,60,480,350);
        Panel.add(scrollpane);

        //  上架鲜花
        addflowerLabel= new JLabel("鲜花名称");
        addflowerLabel.setBounds(50,420,60,30);
        Panel.add(addflowerLabel);
        addflowerfield= new JTextField();
        addflowerfield.setBounds(50,450,60,30);
        Panel.add(addflowerfield);

        addinPriceLable= new JLabel("鲜花进价");
        addinPriceLable.setBounds(150,420,60,30);
        Panel.add(addinPriceLable);
        addinPricefield = new JTextField();
        addinPricefield.setBounds(150,450,60,30);
        Panel.add(addinPricefield);

        addoutPriceLable = new JLabel("鲜花售价");
        addoutPriceLable.setBounds(250,420,60,30);
        Panel.add(addoutPriceLable);
        addoutPricefield =  new JTextField();
        addoutPricefield.setBounds(250,450,60,30);
        Panel.add(addoutPricefield);

        addnumberLable = new JLabel("鲜花数目");
        addnumberLable.setBounds(350,420,60,30);
        Panel.add(addnumberLable);
        addnumberfield = new JTextField();
        addnumberfield.setBounds(350,450,60,30);
        Panel.add(addnumberfield);

        addButton = new JButton("上架鲜花");
        addButton.setBounds(450,450,100,30);
        Panel.add(addButton);
        addButton.addActionListener(this);

        findAllflwoers= new JButton("查询所有鲜花");
        findAllflwoers.setBounds(100,500,120,30);
        findAllflwoers.addActionListener(this);
        Panel.add(findAllflwoers);

        findAllOrders = new JButton("查询所有订单");
        findAllOrders.setBounds(350,500,120,30);
        findAllOrders.addActionListener(this);
        Panel.add(findAllOrders);

        this.add(Panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==updatebutton){
            if(numField==null || numField.getText().length()==0){
                JOptionPane.showMessageDialog(null, "库存/价格不能为空!", "提示", JOptionPane. ERROR_MESSAGE);
                return;
            }
            String flower = (String)comboBox.getSelectedItem();
            String function= (String) updateBox.getSelectedItem();
            if(function.equals("库存")){
                long num = Long.valueOf(numField.getText());
                flowerService.updateFlowerNumber(flower,num);
                JOptionPane.showMessageDialog(null, flower+"库存修改成功 !","提示",JOptionPane.INFORMATION_MESSAGE);
            }
            if(function.equals("售价")){
                double Price = Double.valueOf(numField.getText());
                flowerstoreService.updateflowerstoreOutPrince(flower,Price);
                JOptionPane.showMessageDialog(null, flower+"售价修改成功 !","提示",JOptionPane.INFORMATION_MESSAGE);
            }
            numField.setText("");
            Vector vdate = new Vector();
            List<Flower> flowers=flowerService.findAllFlower();
            List<Flowerstore> allFlower1 = flowerstoreService.findAllFlower();
            for(int i=0;i<flowers.size();i++){
                Vector v = new Vector();
                Flower flower1 = flowers.get(i);
                Flowerstore flowerstore = allFlower1.get(i);
                v.add(flower1.getName());
                v.add(flower1.getInPrice());
                v.add(flowerstore.getOutPrice());
                v.add(flower1.getTotalNumber());
                v.add(flower1.getBuyNumber());
                vdate.add(v);
            }
            ft = new DefaultTableModel(vdate,vNames);
            table.setModel(ft);
        }
        if(e.getSource()==addButton){
            if(addflowerfield.getText()==null || addflowerfield.getText().length()==0 ||
               addinPricefield.getText()==null || addinPricefield.getText().length() == 0 ||
                addoutPricefield.getText() == null || addoutPricefield.getText().length()==0 ||
                addnumberfield.getText() == null || addnumberfield.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "输入框不能为空!", "提示", JOptionPane. ERROR_MESSAGE);
                return ;
            }
            String flowerName= addflowerfield.getText();
            double inPrice = Double.parseDouble(addinPricefield.getText());
            double outPrice= Double.parseDouble(addoutPricefield.getText());
            long num= Long.parseLong(addnumberfield.getText());
            flowerService.addFlower(new Flower(flowerName,inPrice,num,0));
            flowerstoreService.addflowerstore(new Flowerstore(flowerName,outPrice));
            Vector vdate = new Vector();
            List<Flower> flowers=flowerService.findAllFlower();
            List<Flowerstore> allFlower1 = flowerstoreService.findAllFlower();
            for(int i=0;i<flowers.size();i++){
                Vector v = new Vector();
                Flower flower1 = flowers.get(i);
                Flowerstore flowerstore = allFlower1.get(i);
                v.add(flower1.getName());
                v.add(flower1.getInPrice());
                v.add(flowerstore.getOutPrice());
                v.add(flower1.getTotalNumber());
                v.add(flower1.getBuyNumber());
                vdate.add(v);
            }
            ft = new DefaultTableModel(vdate,vNames);
            table.setModel(ft);
            addflowerfield.setText("");
            addinPricefield.setText("");
            addoutPricefield.setText("");
            addnumberfield.setText("");
            comboBox.addItem(flowerName);
            JOptionPane.showMessageDialog(null, "新增鲜花成功!","提示",JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource()==findAllOrders){
            Vector vorderdata = new Vector();
            List<Orders> orders = ordersService.findAllOrders();
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
        if(e.getSource()==findAllflwoers){
            Vector vdate = new Vector();
            List<Flower> flowers=flowerService.findAllFlower();
            List<Flowerstore> allFlower1 = flowerstoreService.findAllFlower();
            for(int i=0;i<flowers.size();i++){
                Vector v = new Vector();
                Flower flower1 = flowers.get(i);
                Flowerstore flowerstore = allFlower1.get(i);
                v.add(flower1.getName());
                v.add(flower1.getInPrice());
                v.add(flowerstore.getOutPrice());
                v.add(flower1.getTotalNumber());
                v.add(flower1.getBuyNumber());
                vdate.add(v);
            }
            ft = new DefaultTableModel(vdate,vNames);
            table.setModel(ft);
        }
    }
}
