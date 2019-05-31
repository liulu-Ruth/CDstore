package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class Stock extends JFrame {

    private JPanel contentPane;
    Stock THIS;
    public CDInfo cdInfo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Stock bFrame = new Stock();
                    bFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Stock() throws SQLException {
        THIS = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        final Rectangle frameBounds = new Rectangle(
                screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        //setBounds(100 + 400, 100 + 10, 308, 500);
        setBounds(frameBounds);
        setTitle("Stock");
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
        jScrollPane.setBounds(0, 0, this.getWidth() - 400, this.getHeight() - 300);
        panel1.add(jScrollPane, BorderLayout.NORTH);

        ResultSet resultSet = CDJdbc.selectstock();
        while (resultSet.next()) {
            String[] addData = {String.valueOf(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), String.valueOf(resultSet.getDouble(4)), String.valueOf(resultSet.getInt(5)), String.valueOf(resultSet.getInt(6))};
            tableModel.addRow(addData);
        }

        Color labColor = new Color(153, 183, 111);
        Font labFont = new Font("幼圆", Font.BOLD, 15);
        JLabel barcodeLabel = new JLabel("条码");
        barcodeLabel.setFont(labFont);
        barcodeLabel.setBounds(250, 100, 50, 30);
        barcodeLabel.setBackground(labColor);
        panel.add(barcodeLabel);

        Color txColor = new Color(207, 218, 210);
        JTextField barcodeField;
        barcodeField = new JTextField();
        barcodeField.setForeground(Color.BLACK);
        barcodeField.setBounds(300, 100, 200, 30);
        barcodeField.setBackground(txColor);
        panel.add(barcodeField);
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


        Color btnColor = new Color(153, 183, 111);
        Font btnFont = new Font("幼圆", Font.BOLD, 15);
        JButton backButton = new JButton("返回");
        backButton.setFont(btnFont);
        backButton.setBackground(btnColor);
        backButton.setBounds(20, 20, 200, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buyer buyer = null;
                buyer = new Buyer();
                buyer.setVisible(true);
                THIS.dispose();
            }
        });
        panel.add(backButton);

        JButton confirmButton = new JButton("搜索");
        confirmButton.setFont(btnFont);
        confirmButton.setBackground(btnColor);
        confirmButton.setBounds(800, 100, 200, 30);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (barcodeField.getText().length() != 13) {
                    JOptionPane.showMessageDialog(null, "请输入正确的条码");
                } else {
                    ResultSet resultSet1 = CDJdbc.selectbarcode(barcodeField.getText());
                    tableModel.setRowCount(0);
                    String[] addData = new String[0];
                    try {
                        while (resultSet1.next()) {
                            System.out.println(resultSet1.getString(1));
                            addData = new String[]{String.valueOf(resultSet1.getString(1)), resultSet1.getString(2), resultSet1.getString(3), String.valueOf(resultSet1.getDouble(4)), String.valueOf(resultSet1.getInt(5)), String.valueOf(resultSet1.getInt(6))};
                            tableModel.addRow(addData);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        panel.add(confirmButton);
        JButton addButton = new JButton("添加");
        addButton.setFont(btnFont);
        addButton.setBackground(btnColor);
        addButton.setBounds(300, this.getHeight() - 100, 200, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCD addCD = new AddCD();
                addCD.setVisible(true);
            }
        });
        panel.add(addButton);

        JButton completeButton = new JButton("修改");
        completeButton.setFont(btnFont);
        completeButton.setBackground(btnColor);
        completeButton.setBounds(this.getWidth() - 500, this.getHeight() - 100, 200, 30);
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();// 获得被选中行的索引
                if (row == -1) {
                    //CDJdbc.leaseUpdata(Integer.parseInt(table.getValueAt(row,3).toString()),table.getValueAt(row,1).toString(),1);
                    JOptionPane.showMessageDialog(null, "未选行");

                } else {
                    cdInfo = new CDInfo(table.getValueAt(row, 0).toString(), table.getValueAt(row, 1).toString(), table.getValueAt(row, 2).toString(), Double.valueOf(table.getValueAt(row, 3).toString()), Integer.parseInt(table.getValueAt(row, 4).toString()), Integer.parseInt(table.getValueAt(row, 5).toString()));
                    Modify modify = new Modify(cdInfo);
                    modify.setVisible(true);
                    //JOptionPane.showMessageDialog(null,"归还失败");
                }
            }
        });
        panel.add(completeButton);

        JButton refreshButton = new JButton("刷新");
        refreshButton.setFont(btnFont);
        refreshButton.setBackground(btnColor);
        refreshButton.setBounds(this.getWidth() - 730, this.getHeight() - 100, 200, 30);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                ResultSet resultSet = CDJdbc.selectstock();
                try {
                    while (resultSet.next()) {
                        System.out.println("next");
                        String[] addData = new String[0];
                        addData = new String[]{String.valueOf(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), String.valueOf(resultSet.getDouble(4)), String.valueOf(resultSet.getInt(5)), String.valueOf(resultSet.getInt(6))};
                        tableModel.addRow(addData);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            //JOptionPane.showMessageDialog(null,"归还失败");

        });
        panel.add(refreshButton);


    }

}
