package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Sale extends JFrame {
    private JPanel contentPane;
    Sale THIS;
    public static SaleInfo saleInfo;
    public static CDInfo cdInfo;
    public static String name;
    public static String phone;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Sale sFrame = new Sale();
                    sFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Sale(){
        THIS=this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        final Rectangle frameBounds = new Rectangle(
                screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        //setBounds(100 + 400, 100 + 10, 308, 500);
        setBounds(frameBounds);
        setTitle("Cashier");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //  /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片
                ImageIcon icon = new ImageIcon(getClass().getResource("pho\\qxdyjj.jpg"));

                Image img = icon.getImage();
                g.drawImage(img, 0, 0, screenSize.width, screenSize.height, this);
            }
        };
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel1=new JPanel();
        panel1.setBounds(250,150,this.getWidth()-400,this.getHeight()-300);
        panel1.setLayout(null);
        panel.add(panel1);

        Color labColor = new Color(153, 183, 111);
        Font labFont=new Font("幼圆",Font.BOLD,15);
        JLabel nameLabel = new JLabel( "会员卡号");
        nameLabel.setFont(labFont);
        nameLabel.setBounds(200, 100, 50, 30);
        nameLabel.setBackground(labColor);
        panel.add(nameLabel);

        JLabel phoneLabel = new JLabel( "手机号");
        phoneLabel.setFont(labFont);
        phoneLabel.setBounds(500, 100, 50, 30);
        phoneLabel.setBackground(labColor);
        panel.add(phoneLabel);

        JLabel cdLabel = new JLabel( "条码");
        cdLabel.setFont(labFont);
        cdLabel.setBounds(200, this.getHeight()-100, 50, 30);
        cdLabel.setBackground(labColor);
        panel.add(cdLabel);

        Color txColor = new Color(207,218,210);
        JTextField nameField;
        nameField = new JTextField();
        nameField.setForeground(Color.BLACK);
        nameField.setBounds(250, 100, 200, 30);
        nameField.setBackground(txColor);
        panel.add(nameField);
        nameField.setColumns(10);

        JTextField phoneField;
        phoneField = new JTextField();
        phoneField.setForeground(Color.BLACK);
        phoneField.setBounds(550, 100, 200, 30);
        phoneField.setBackground(txColor);
        panel.add(phoneField);
        phoneField.setColumns(11);

        JTextField cdField;
        cdField = new JTextField();
        cdField.setForeground(Color.BLACK);
        cdField.setBounds(250, this.getHeight()-100, 200, 30);
        cdField.setBackground(txColor);
        panel.add(cdField);
        cdField.setColumns(13);

        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Vector<String> coloumNames = new Vector<String>(1);

        coloumNames.add("条码");//名称 数量 会员价 金额
        coloumNames.add("名称");
        coloumNames.add("数量");
        coloumNames.add("会员价");
        coloumNames.add("金额");

        DefaultTableModel tableModel = new DefaultTableModel(dataVector, coloumNames){
            public boolean isCellEditable(int row, int col){
                if(col!=2)
                    return false;
                else
                    return true;
            }
        };
        JTable table=new JTable(tableModel);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0,0,this.getWidth()-400,this.getHeight()-300);
        /*table.setRowSorter(new TableRowSorter<TableModel>(tableModel));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);*/
        //Object[][] tableObj=new Object[][];
        //table.setBounds(100,100,300,300);
        //table.setBackground(Color.BLUE);
        panel1.add(jScrollPane, BorderLayout.NORTH);
        /*TableRowSorter<DefaultTableModel> sorter=new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);*/

        Color btnColor = new Color(153, 183, 111);
        Font btnFont=new Font("幼圆",Font.BOLD,15);
        JButton confirmButton = new JButton("确定");
        confirmButton.setFont(btnFont);
        confirmButton.setBackground(btnColor);
        confirmButton.setBounds(800, 100, 200, 30);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name=nameField.getText();
                phone=phoneField.getText();
                if(name.isEmpty()){
                    JOptionPane.showMessageDialog(null, "请输入姓名");
                }
                else if (phone.isEmpty()){
                    JOptionPane.showMessageDialog(null, "请输入手机号");
                }
                else if (phone.length()!=11){
                    JOptionPane.showMessageDialog(null, "手机号无效");
                }else {
                    JOptionPane.showMessageDialog(null, "成功");
                }
            }
        });
        panel.add(confirmButton);
        JButton addButton = new JButton("添加");
        addButton.setFont(btnFont);
        addButton.setBackground(btnColor);
        addButton.setBounds(600, this.getHeight()-100, 200, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String barcode=cdField.getText();
                if (barcode.length()!=13){
                    JOptionPane.showMessageDialog(null,"条码无效");
                }
                else {
                    cdInfo=new CDInfo(barcode,"","",0,0,-100);
                    System.out.println(barcode);
                    System.out.println(cdInfo.getCdbarcode());
                    System.out.println("hi");
                    cdInfo.setCdbarcode(barcode);
                    if (CDJdbc.select(cdInfo)){
                        int i;
                        for(i=0;i<table.getRowCount();i++){
                            if(table.getValueAt(i,0).equals(cdInfo.getCdbarcode())){
                                table.setValueAt((Integer.parseInt(table.getValueAt(i, 2).toString()) + 1) + "", i, 2);
                                break;
                            }
                        }
                        System.out.println("i:"+i+" table.getRowCount():"+table.getRowCount());
                        if(i==table.getRowCount()){
                            DecimalFormat df = new DecimalFormat(".00");
                            String[] addData = {cdInfo.getCdbarcode(), cdInfo.getName(), 1+"" ,String.valueOf(cdInfo.getPrice()*0.8),String.valueOf(cdInfo.getPrice())};//获取文本框中的内容
                            //String[] addData = {cdInfo.getCdbarcode(), cdInfo.getName(), 1+"" ,String.valueOf(df.format(cdInfo.getPrice()/10))};//获取文本框中的内容
                            tableModel.addRow(addData);//将文本框中的内容添加到表格模型中的末尾一行
                        }
                        //int[] num = new int[50];
                        //int row = table.getRowCount() + 1;//获取表格的行数
                    }else {
                        JOptionPane.showMessageDialog(null,"条码错误");
                    }
                }
            }
        });
        panel.add(addButton);
        JButton settlementButton = new JButton("结算");
        settlementButton.setFont(btnFont);
        settlementButton.setBackground(btnColor);
        settlementButton.setBounds(this.getWidth()-300, this.getHeight()-100, 200, 30);
        settlementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(name.isEmpty()||phone.isEmpty()){
                    JOptionPane.showMessageDialog(null,"请填写客户信息");
                }else {
                    int total=0;
                    for (int i=0;i<table.getRowCount();i++){
                        total+=Integer.parseInt(table.getValueAt(i,2).toString());
                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        sd.format(new Date());
                        SimpleDateFormat rd = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                        Calendar c = Calendar.getInstance();
                        c.add(Calendar.DAY_OF_MONTH, 10);
                        Date rdate=c.getTime();
                        if (i==table.getRowCount()-1){
                            //leaseInfo=new LeaseInfo(0,table.getValueAt(i,0).toString(),Integer.parseInt(table.getValueAt(i,2).toString()),name,phone,Integer.parseInt(table.getValueAt(i,3).toString())*Integer.parseInt(table.getValueAt(i,2).toString()),(total+4)/5*50,new Date(),rdate);
                        }else {
                            //leaseInfo=new LeaseInfo(0,table.getValueAt(i,0).toString(),Integer.parseInt(table.getValueAt(i,2).toString()),name,phone,Integer.parseInt(table.getValueAt(i,3).toString())*Integer.parseInt(table.getValueAt(i,2).toString()),0,new Date(),rdate);
                        }
                    }
                }
            }
        });
        panel.add(settlementButton);
    }

}