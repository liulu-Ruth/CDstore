package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ReturnReminder extends JFrame {
    private JPanel contentPane;
    ReturnReminder THIS;

    public static void main(String[] args) {
        // write your code here
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReturnReminder returnReminder = new ReturnReminder();
                    returnReminder.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ReturnReminder() throws SQLException {
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
        JLabel phoneLabel = new JLabel("请提醒以下用户尽快归还商品，否则将收取逾期罚款！");
        phoneLabel.setFont(labFont);
        phoneLabel.setBounds(this.getWidth()/2-125, 100, 250, 30);
        phoneLabel.setBackground(labColor);
        panel.add(phoneLabel);


        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Vector<String> coloumNames = new Vector<String>(1);

        coloumNames.add("序号");
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

        ResultSet resultSet = LeaseJdbc.selectreturn();
        while (resultSet.next()) {
            String[] addData = {String.valueOf(resultSet.getInt(1)), resultSet.getString(2), resultSet.getString(4), String.valueOf(resultSet.getInt(3)), resultSet.getString(5), String.format("%.2f", resultSet.getDouble(6)), String.format("%.2f", resultSet.getDouble(7)), String.valueOf(resultSet.getTimestamp(8)), String.valueOf(resultSet.getDate(9))};
            tableModel.addRow(addData);
        }

        Color btnColor = new Color(153, 183, 111);
        Font btnFont = new Font("幼圆", Font.BOLD, 15);
        JButton delButton = new JButton("确认");
        delButton.setFont(btnFont);
        delButton.setBackground(btnColor);
        delButton.setBounds(this.getWidth()/2-100, this.getHeight()-100, 200, 30);
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                THIS.dispose();
            }
        });
        panel.add(delButton);
    }
}
