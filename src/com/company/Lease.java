package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;


public class Lease extends JFrame {
    private JPanel contentPane;
    Lease THIS;
    public static LeaseInfo leaseInfo;
    public static CDInfo cdInfo;
    public static String name;
    public static String phone;
    public static double change = 0.00;
    public static double totalPrice = 0.00;
    public static double deposit = 0.00;
    public static boolean settlementFlag = true;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Lease lFrame = new Lease();
                    lFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Lease() {
        THIS = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        final Rectangle frameBounds = new Rectangle(
                screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        //setBounds(100 + 400, 100 + 10, 308, 500);
        setBounds(frameBounds);
        setTitle("Lease");
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

        JPanel panel1 = new JPanel();
        panel1.setBounds(250, 150, this.getWidth() - 400, this.getHeight() - 300);
        panel1.setLayout(null);
        panel.add(panel1);

        Color labColor = new Color(153, 183, 111);
        Font labFont = new Font("幼圆", Font.BOLD, 15);
        JLabel nameLabel = new JLabel("姓名");
        nameLabel.setFont(labFont);
        nameLabel.setBounds(200, 100, 50, 30);
        nameLabel.setBackground(labColor);
        panel.add(nameLabel);

        JLabel phoneLabel = new JLabel("手机号");
        phoneLabel.setFont(labFont);
        phoneLabel.setBounds(500, 100, 50, 30);
        phoneLabel.setBackground(labColor);
        panel.add(phoneLabel);

        JLabel cdLabel = new JLabel("条码");
        cdLabel.setFont(labFont);
        cdLabel.setBounds(200, this.getHeight() - 150, 50, 30);
        cdLabel.setBackground(labColor);
        panel.add(cdLabel);

        JLabel cashLabel = new JLabel("现金");
        cashLabel.setFont(labFont);
        cashLabel.setBounds(200, this.getHeight() - 100, 50, 30);
        cashLabel.setBackground(labColor);
        panel.add(cashLabel);

        Color txColor = new Color(207, 218, 210);
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
        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int keyChar = e.getKeyChar();
                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
                } else {
                    e.consume();
                }
            }
        });


        JTextField cdField;
        cdField = new JTextField();
        cdField.setForeground(Color.BLACK);
        cdField.setBounds(250, this.getHeight() - 150, 200, 30);
        cdField.setBackground(txColor);
        panel.add(cdField);
        cdField.setColumns(13);

        JTextField cashField;
        cashField = new JTextField();
        cashField.setForeground(Color.BLACK);
        cashField.setBounds(250, this.getHeight() - 100, 200, 30);
        cashField.setBackground(txColor);
        panel.add(cashField);
        cashField.setColumns(13);


        /*存入LeaseInfo*/
        Vector<LeaseInfo> leaseInfos = new Vector<>();

        /*顾客租的商品JTable*/
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Vector<String> coloumNames = new Vector<String>(1);

        coloumNames.add("条码");//名称 数量 会员价 金额
        coloumNames.add("名称");
        coloumNames.add("数量");
        //coloumNames.add("会员价");
        coloumNames.add("金额");

        /*for (int i = 1; i < 4; i++) {
            Vector<String> W = new Vector<String>();
            W.add("A" + i * 10);
            W.add("B" + i * 10);
            dataVector.add(W);
        }*/
        DefaultTableModel tableModel = new DefaultTableModel(dataVector, coloumNames) {
            public boolean isCellEditable(int row, int col) {
                if (col != 2)
                    return false;
                else
                    return true;
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0, 0, this.getWidth() - 400, this.getHeight() - 400);
        /*table.setRowSorter(new TableRowSorter<TableModel>(tableModel));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);*/
        //Object[][] tableObj=new Object[][];
        //table.setBounds(100,100,300,300);
        //table.setBackground(Color.BLUE);
        panel1.add(jScrollPane, BorderLayout.NORTH);
        /*TableRowSorter<DefaultTableModel> sorter=new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);*/


        /*结算的JTable*/
        Vector<Vector<String>> sVector = new Vector<Vector<String>>(1);
        Vector<String> seColoumNames = new Vector<String>(1);

        seColoumNames.add("金额");//名称 数量 会员价 金额
        seColoumNames.add("押金");
        seColoumNames.add("合计");
        seColoumNames.add("现金");
        seColoumNames.add("找零");
        //String[] seColoumNames={"金额","押金","合计","现金","找零"};
        DefaultTableModel tableModel1 = new DefaultTableModel(sVector, seColoumNames) {
            public boolean isCellEditable(int row, int col) {
                    return false;
            }
        };
        JTable table1 = new JTable(tableModel1);
        JScrollPane jScrollPane1 = new JScrollPane(table1);
        jScrollPane1.setBounds(0, this.getHeight() - 400, this.getWidth() - 400, 100);
        panel1.add(jScrollPane1, BorderLayout.NORTH);


        Color btnColor = new Color(153, 183, 111);
        Font btnFont = new Font("幼圆", Font.BOLD, 15);

        JButton backButton = new JButton("返回");
        backButton.setFont(btnFont);
        backButton.setBackground(btnColor);
        backButton.setBounds(20,20,200,30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier cashier = new Cashier();
                cashier.setVisible(true);
                THIS.dispose();
            }
        });
        panel.add(backButton);

        JButton confirmButton = new JButton("确定");
        confirmButton.setFont(btnFont);
        confirmButton.setBackground(btnColor);
        confirmButton.setBounds(800, 100, 200, 30);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameField.getText();
                phone = phoneField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入姓名");
                } else if (phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入手机号");
                } else if (phone.length() != 11) {
                    JOptionPane.showMessageDialog(null, "手机号无效");
                } else {
                    JOptionPane.showMessageDialog(null, "成功");
                }
            }
        });
        panel.add(confirmButton);
        JButton addButton = new JButton("添加");
        addButton.setFont(btnFont);
        addButton.setBackground(btnColor);
        addButton.setBounds(600, this.getHeight() - 150, 200, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String barcode = cdField.getText();
                if (barcode.length() != 13) {
                    JOptionPane.showMessageDialog(null, "条码无效");
                } else {
                    cdInfo = new CDInfo(barcode, "", "", 0, 0, -100);
                    System.out.println(barcode);
                    System.out.println(cdInfo.getCdbarcode());
                    System.out.println("hi");
                    cdInfo.setCdbarcode(barcode);
                    if (CDJdbc.select(cdInfo,"lease")) {
                        int i;
                        for (i = 0; i < table.getRowCount(); i++) {
                            if (table.getValueAt(i, 0).equals(cdInfo.getCdbarcode())) {
                                if (cdInfo.getLeasestock()>Integer.parseInt(table.getValueAt(i, 2).toString()) + 1) {
                                    table.setValueAt((Integer.parseInt(table.getValueAt(i, 2).toString()) + 1) + "", i, 2);
                                    break;
                                }else {
                                    JOptionPane.showMessageDialog(null,"库存不足！");
                                }
                            }
                        }
                        System.out.println("i:" + i + " table.getRowCount():" + table.getRowCount());
                        if (i == table.getRowCount()) {
                            if (cdInfo.getLeasestock()>1) {
                                //cdInfo.getLeasestock()>Integer.parseInt(table.getValueAt(i, 2).toString()) + 1
                                DecimalFormat df = new DecimalFormat(".00");
                                //String[] addData = {cdInfo.getCdbarcode(), cdInfo.getName(), 1+"" ,String.valueOf(cdInfo.getPrice()*0.8),String.valueOf(cdInfo.getPrice())};//获取文本框中的内容
                                String[] addData = {cdInfo.getCdbarcode(), cdInfo.getName(), 1 + "", String.valueOf(df.format(cdInfo.getPrice() / 10))};//获取文本框中的内容
                                tableModel.addRow(addData);//将文本框中的内容添加到表格模型中的末尾一行
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"库存不足！");
                            }
                        }
                        //int[] num = new int[50];
                        //int row = table.getRowCount() + 1;//获取表格的行数
                    } else {
                        JOptionPane.showMessageDialog(null, "条码错误");
                    }
                }
            }
        });
        panel.add(addButton);

        JButton completeButton = new JButton("完成");
        completeButton.setFont(btnFont);
        completeButton.setBackground(btnColor);
        completeButton.setBounds(this.getWidth() - 300, this.getHeight() - 100, 200, 30);
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i<leaseInfos.size();i++){
                    LeaseJdbc.insert(leaseInfos.get(i));
                    CDJdbc.leaseUpdata(leaseInfos.get(i).getNumber(),leaseInfos.get(i).getCdbarcode());
                }
                change = 0.00;
                totalPrice = 0.00;
                deposit = 0.00;
                settlementFlag=true;
                dataVector.clear();
                sVector.clear();
                Lease lease=new Lease();
                lease.setVisible(true);
                THIS.dispose();
            }
        });
        panel.add(completeButton);

        JButton changeButton = new JButton("找零");
        changeButton.setFont(btnFont);
        changeButton.setBackground(btnColor);
        changeButton.setBounds(600, this.getHeight() - 100, 200, 30);
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setValueAt(cashField.getText(), 0, 3);
                System.out.println(table1.getValueAt(0, 3));
                System.out.println(table1.getValueAt(0, 3).toString());
                System.out.println(Double.valueOf(table1.getValueAt(0, 3).toString()));
                change = Double.valueOf(cashField.getText()) - totalPrice;
                System.out.println(change);
                table1.setValueAt(String.valueOf(change), 0, 4);
            }
        });
        panel.add(changeButton);

        JButton settlementButton = new JButton("结算");
        settlementButton.setFont(btnFont);
        settlementButton.setBackground(btnColor);
        settlementButton.setBounds(this.getWidth() - 300, this.getHeight() - 150, 200, 30);
        settlementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请填写客户信息");
                } else if (settlementFlag == false) {
                    JOptionPane.showMessageDialog(null, "您已结算");

                } else if (settlementFlag == true) {
                    int isSettle = JOptionPane.showConfirmDialog(null, "是否确认结算？", "提示", JOptionPane.YES_NO_OPTION);
                    if (isSettle == JOptionPane.YES_OPTION) {
                        settlementFlag = false;
                        int total = 0;
                        for (int i = 0; i < table.getRowCount(); i++) {
                            total += Integer.parseInt(table.getValueAt(i, 2).toString());
                            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                            Date nd=new Date();
                            sd.format(nd);
                            try {
                                nd=sd.parse(sd.format(new Date()));
                                System.out.println("zhe shi nd :"+nd);
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }

                            System.out.println("zhe shi nd :"+nd);
                            System.out.println(sd.format(nd));

                            SimpleDateFormat rd = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                            Calendar c = Calendar.getInstance();
                            c.add(Calendar.DAY_OF_MONTH, 10);
                            Date rdate = (Date) c.getTime();
                            System.out.println(nd);
                            System.out.println(rdate);
                            System.out.println("Double.parseDouble(table.getValueAt(i, 3).toString()):"+Double.parseDouble(table.getValueAt(i, 3).toString()));
                            System.out.println("Double.parseDouble(table.getValueAt(i, 2).toString():"+Double.parseDouble(table.getValueAt(i, 2).toString()));
                            totalPrice += Double.parseDouble(table.getValueAt(i, 3).toString()) * Double.parseDouble(table.getValueAt(i, 2).toString());
                            if (i == table.getRowCount() - 1) {
                                System.out.println(table.getValueAt(i, 0).toString());
                                leaseInfo = new LeaseInfo(0, table.getValueAt(i, 0).toString(), Integer.parseInt(table.getValueAt(i, 2).toString()), name, phone, Double.parseDouble(table.getValueAt(i, 3).toString()) * Double.parseDouble(table.getValueAt(i, 2).toString()), (total + 4) / 5 * 50, nd, rdate);
                                leaseInfos.add(leaseInfo);
                            } else {
                                leaseInfo = new LeaseInfo(0, table.getValueAt(i, 0).toString(), Integer.parseInt(table.getValueAt(i, 2).toString()), name, phone, Double.parseDouble(table.getValueAt(i, 3).toString()) * Double.parseDouble(table.getValueAt(i, 2).toString()), 0, nd, rdate);
                                leaseInfos.add(leaseInfo);
                            }
                        }
                        deposit = (total + 4) / 5 * 50;
                        totalPrice += deposit;
                        String[] addData = {String.format("%.2f",(totalPrice-deposit)),String.valueOf(deposit) , String.valueOf(totalPrice), "",""};
                        tableModel1.addRow(addData);//将文本框中的内容添加到表格模型中的末尾一行

                        //ListSelectionModel isEmpyt = table.getSelectionModel();
                        /*if(table1.getValueAt(0,3).toString().isEmpty()){
                            try {
                                sleep(10000);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            if(!table1.getValueAt(0,3).toString().isEmpty()) {
                                System.out.println("wanle");
                                System.out.println(table1.getValueAt(0, 3));
                                System.out.println(table1.getValueAt(0, 3).toString());
                                System.out.println(Double.valueOf(table1.getValueAt(0, 3).toString()));
                                change = Double.valueOf(table1.getValueAt(0, 3).toString()) - totalPrice;
                                System.out.println(change);
                                table1.setValueAt(String.valueOf(change), 0, 4);
                            }
                        }*/

                    }
                }
            }
        });
        panel.add(settlementButton);
    }
}
