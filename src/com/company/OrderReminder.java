package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class OrderReminder extends JFrame {
    private JPanel contentPane;
    OrderReminder THIS;

    public static void main(String[] args) {
        // write your code here
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrderReminder returnReminder = new OrderReminder();
                    returnReminder.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public OrderReminder() throws SQLException {
        THIS = this;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        final Rectangle frameBounds = new Rectangle(
                screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        //setBounds(100 , 100 , 308, 500);
        setBounds(frameBounds);
        setTitle("ReturnReminder");
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
        Font labFont = new Font("幼圆", Font.BOLD, 25);
        JLabel hintLabel = new JLabel("以下商品库存不足，请尽快采购！");
        hintLabel.setFont(labFont);
        hintLabel.setBounds(this.getWidth()/2-125, 100, 650, 30);
        hintLabel.setBackground(labColor);
        panel.add(hintLabel);

        JLabel hintLabel1 = new JLabel("以下商品库存充裕，请避免采购！");
        hintLabel1.setFont(labFont);
        hintLabel1.setBounds(270, 150, 650, 30);
        hintLabel1.setBackground(labColor);
        panel1.add(hintLabel1);

        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Vector<String> coloumNames = new Vector<String>(1);

        coloumNames.add("条码");
        coloumNames.add("名称");
        coloumNames.add("数量");
        coloumNames.add("价格");
        coloumNames.add("销售库存");
        coloumNames.add("租赁库存");

        DefaultTableModel tableModel = new DefaultTableModel(dataVector, coloumNames) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0, 0, this.getWidth() - 400, 200);
        panel1.add(jScrollPane, BorderLayout.NORTH);

        ResultSet resultSet = CDJdbc.selectorder(0);
        while (resultSet.next()) {
            String[] addData = {String.valueOf(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), String.valueOf(resultSet.getDouble(4)), String.valueOf(resultSet.getInt(5)), String.valueOf(resultSet.getInt(6))};
            tableModel.addRow(addData);
        }

        Vector<Vector<String>> dataVector1 = new Vector<Vector<String>>();
        Vector<String> coloumNames1 = new Vector<String>(1);

        coloumNames1.add("条码");
        coloumNames1.add("名称");
        coloumNames1.add("数量");
        coloumNames1.add("价格");
        coloumNames1.add("销售库存");
        coloumNames1.add("租赁库存");

        DefaultTableModel tableModel1 = new DefaultTableModel(dataVector1, coloumNames1) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        JTable table1 = new JTable(tableModel1);
        JScrollPane jScrollPane1 = new JScrollPane(table1);
        jScrollPane1.setBounds(0, 200, this.getWidth() - 400, 250);
        panel1.add(jScrollPane1, BorderLayout.NORTH);

        ResultSet resultSet1 = CDJdbc.selectorder1(1);
        while (resultSet1.next()) {
            String[] addData1 = {String.valueOf(resultSet1.getString(1)), resultSet1.getString(2), resultSet1.getString(3), String.valueOf(resultSet1.getDouble(4)), String.valueOf(resultSet1.getInt(5)), String.valueOf(resultSet1.getInt(6))};
            tableModel1.addRow(addData1);
        }

    }
}
