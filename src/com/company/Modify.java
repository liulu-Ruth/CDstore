package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Modify extends JFrame{

    private JPanel contentPane;
    Modify THIS;
    CDInfo cdInfo;

    public Modify(CDInfo cdInfo){

        THIS=this;
        THIS.cdInfo=cdInfo;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        final Rectangle frameBounds = new Rectangle(
                screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        //setBounds(100 + 400, 100 + 10, 308, 500);
        setBounds(frameBounds);
        setTitle("Modify");
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
        panel1.setBounds(250, 150, this.getWidth() - 400, 100);
        panel1.setLayout(null);
        panel.add(panel1);

        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Vector<String> coloumNames = new Vector<String>(1);

        coloumNames.add("条码");//名称 数量 会员价 金额
        coloumNames.add("名称");
        coloumNames.add("类别");
        coloumNames.add("金额");
        coloumNames.add("销售库存");
        coloumNames.add("租赁库存");

        DefaultTableModel tableModel = new DefaultTableModel(dataVector, coloumNames) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0, 0, this.getWidth() - 400, 100);
        panel1.add(jScrollPane, BorderLayout.NORTH);

        String[] addData = {cdInfo.getCdbarcode(),cdInfo.getName(),cdInfo.getCategory(),String.valueOf(cdInfo.getPrice()), String.valueOf(cdInfo.getSalestock()), String.valueOf(cdInfo.getLeasestock())};
        tableModel.addRow(addData);

        Color labColor = new Color(153, 183, 111);
        Font labFont = new Font("幼圆", Font.BOLD, 15);
        JLabel barcodeLabel = new JLabel("条码");
        barcodeLabel.setFont(labFont);
        barcodeLabel.setBounds(250, 200, 50, 30);
        barcodeLabel.setBackground(labColor);
        //panel.add(barcodeLabel);

        JLabel nameLabel = new JLabel("名称");
        nameLabel.setFont(labFont);
        nameLabel.setBounds(250, 300, 50, 30);
        nameLabel.setBackground(labColor);
        //panel.add(nameLabel);

        JLabel categoryLabel = new JLabel("类别");
        categoryLabel.setFont(labFont);
        categoryLabel.setBounds(250,400,50,30);
        categoryLabel.setBackground(labColor);
        //panel.add(categoryLabel);

        JLabel priceLabel = new JLabel("金额");
        priceLabel.setFont(labFont);
        priceLabel.setBounds(250,400,50,30);
        priceLabel.setBackground(labColor);
        panel.add(priceLabel);

        JLabel ssLabel = new JLabel("销售库存");
        ssLabel.setFont(labFont);
        ssLabel.setBounds(720,500,80,30);
        ssLabel.setBackground(labColor);
        panel.add(ssLabel);

        JLabel lsLabel = new JLabel("租赁库存");
        lsLabel.setFont(labFont);
        lsLabel.setBounds(720,400,80,30);
        lsLabel.setBackground(labColor);
        panel.add(lsLabel);


        Color txColor = new Color(207, 218, 210);
        JTextField barcodeField;
        barcodeField = new JTextField();
        barcodeField.setForeground(Color.BLACK);
        barcodeField.setBounds(300, 200, 200, 30);
        barcodeField.setBackground(txColor);
        //panel.add(barcodeField);
        barcodeField.setColumns(13);
        barcodeField.addKeyListener(new KeyAdapter() {
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

        JTextField nameField;
        nameField = new JTextField();
        nameField.setForeground(Color.BLACK);
        nameField.setBounds(300, 300, 200, 30);
        nameField.setBackground(txColor);
        //panel.add(nameField);
        nameField.setColumns(10);

        JTextField priceField;
        priceField = new JTextField();
        priceField.setForeground(Color.BLACK);
        priceField.setBounds(300, 400, 200, 30);
        priceField.setBackground(txColor);
        panel.add(priceField);
        priceField.setColumns(13);

        JTextField ssField;
        ssField = new JTextField();
        ssField.setForeground(Color.BLACK);
        ssField.setBounds(800, 500, 200, 30);
        ssField.setBackground(txColor);
        panel.add(ssField);
        ssField.setColumns(7);
        ssField.addKeyListener(new KeyAdapter() {
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

        JTextField lsField;
        lsField = new JTextField();
        lsField.setForeground(Color.BLACK);
        lsField.setBounds(800, 400, 200, 30);
        lsField.setBackground(txColor);
        panel.add(lsField);
        lsField.setColumns(7);
        lsField.addKeyListener(new KeyAdapter() {
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


        String[] listData = new String[]{"CD", "游戏", "程序"};
        final JComboBox<String> comboBox = new JComboBox<String>(listData);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("选中: " + comboBox.getSelectedIndex() + " = " + comboBox.getSelectedItem());
                }
            }
        });
        comboBox.setSelectedIndex(2);
        comboBox.setBounds(300,400,200,30);
        //panel.add(comboBox);


        Color btnColor = new Color(153, 183, 111);
        Font btnFont = new Font("幼圆", Font.BOLD, 15);


        JButton confirmButton = new JButton("确定");
        confirmButton.setFont(btnFont);
        confirmButton.setBackground(btnColor);
        confirmButton.setBounds(800, this.getHeight()-100, 200, 30);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double price=cdInfo.getPrice();
                int ss=cdInfo.getSalestock();
                int ls=cdInfo.getLeasestock();
                System.out.println(cdInfo.getPrice()+" "+cdInfo.getSalestock()+" "+cdInfo.getLeasestock());

                if (priceField.getText().isEmpty()&&ssField.getText().isEmpty()&&lsField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"请填写修改信息");
                } else {
                    if (!priceField.getText().isEmpty()){
                        price=Double.valueOf(priceField.getText());
                    }
                    if (!ssField.getText().isEmpty()){
                        ss=Integer.parseInt(ssField.getText());
                    }
                    if (!lsField.getText().isEmpty()){
                        ls=Integer.parseInt(lsField.getText());
                    }
                    CDInfo cdInfo1=new CDInfo(cdInfo.getCdbarcode(),cdInfo.getName(),cdInfo.getCategory(),price,ss,ls);
                    if (CDJdbc.updata(cdInfo1)){
                        JOptionPane.showMessageDialog(null,"修改成功");
                        tableModel.setRowCount(0);
                        String[] addData= {cdInfo1.getCdbarcode(),cdInfo1.getName(),cdInfo1.getCategory(),String.valueOf(cdInfo1.getPrice()),String.valueOf(cdInfo1.getSalestock()),String.valueOf(cdInfo1.getLeasestock())};
                        tableModel.addRow(addData);
                    }
                }
            }
        });
        panel.add(confirmButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.setFont(btnFont);
        cancelButton.setBackground(btnColor);
        cancelButton.setBounds(400, this.getHeight()-100, 200, 30);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                THIS.setVisible(false);
                THIS.dispose();
            }
        });
        panel.add(cancelButton);

    }
}
