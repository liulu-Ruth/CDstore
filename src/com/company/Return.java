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
import java.util.Vector;
import java.sql.SQLException;

public class Return extends JFrame {
    private JPanel contentPane;
    Return THIS;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Return rFrame = new Return();
                    rFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Return(){
        THIS = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        final Rectangle frameBounds = new Rectangle(
                screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        //setBounds(100 + 400, 100 + 10, 308, 500);
        setBounds(frameBounds);
        setTitle("Sale");
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

        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Vector<String> coloumNames = new Vector<String>(1);

        coloumNames.add("租赁序号");
        coloumNames.add("条码");
        coloumNames.add("姓名");
        coloumNames.add("数量");
        coloumNames.add("手机");
        coloumNames.add("租金");
        coloumNames.add("押金");
        coloumNames.add("租赁日期");
        coloumNames.add("应还日期");

        DefaultTableModel tableModel = new DefaultTableModel(dataVector, coloumNames) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0, 0, this.getWidth() - 400, this.getHeight() - 300);
        panel1.add(jScrollPane, BorderLayout.NORTH);

        Color btnColor = new Color(153, 183, 111);
        Font btnFont = new Font("幼圆", Font.BOLD, 15);

        JButton backButton = new JButton("返回");
        backButton.setFont(btnFont);
        backButton.setBackground(btnColor);
        backButton.setBounds(20,20,200,30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cashier cashier = null;
                try {
                    cashier = new Cashier();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
                if (nameField.getText().isEmpty() && phoneField.getText().length() != 11) {
                    JOptionPane.showMessageDialog(null, "请输入查询信息");
                } else if (!nameField.getText().isEmpty()) {
                    ResultSet resultSet = LeaseJdbc.selectName(nameField.getText());
                    try {
                        if (resultSet.next()){
                            while (resultSet.next()){
                                String[] addData={String.valueOf(resultSet.getInt(1)),resultSet.getString(2),resultSet.getString(4),String.valueOf(resultSet.getInt(3)),resultSet.getString(5),String.format("%.2f",resultSet.getDouble(6)),String.format("%.2f",resultSet.getDouble(7)),String.valueOf(resultSet.getTimestamp(8)),String.valueOf(resultSet.getDate(9))};
                                tableModel.addRow(addData);
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else if (phoneField.getText().length() == 11) {
                    ResultSet resultSet = LeaseJdbc.selectPhone(phoneField.getText());
                    try {
                        while (resultSet.next()) {
                            String[] addData = {String.valueOf(resultSet.getInt(1)), resultSet.getString(2), resultSet.getString(4), String.valueOf(resultSet.getInt(3)), resultSet.getString(5), String.format("%.2f", resultSet.getDouble(6)), String.format("%.2f", resultSet.getDouble(7)), String.valueOf(resultSet.getTimestamp(8)), String.valueOf(resultSet.getDate(9))};
                            tableModel.addRow(addData);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "成功");
                }
            }
        });
        panel.add(confirmButton);

        JButton delButton = new JButton("归还");
        delButton.setFont(btnFont);
        delButton.setBackground(btnColor);
        delButton.setBounds(this.getWidth()/2-100, this.getHeight()-100, 200, 30);
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();// 获得被选中行的索引
                if (LeaseJdbc.delete(Integer.parseInt(table.getValueAt(row,0).toString()))) {
                    CDJdbc.leaseUpdata(Integer.parseInt(table.getValueAt(row,3).toString()),table.getValueAt(row,1).toString(),1);
                    JOptionPane.showMessageDialog(null,"归还成功");
                    if (row != -1) {// 判断是否存在被选中行
                        tableModel.removeRow(row);//从表格模型中删除
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"归还失败");
                }
            }
        });
        panel.add(delButton);
    }
}
