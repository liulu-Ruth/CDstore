package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class Register extends JFrame {

    private JPanel contentPane;
    public static MemberInfo memberInfo;
    Register THIS;

    public static void main(String[] args) {
        // write your code here
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register register = new Register();
                    register.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Register() {
        THIS = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        final Rectangle frameBounds = new Rectangle(
                screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        //setBounds(100 + 400, 100 + 10, 308, 500);
        setBounds(frameBounds);
        setTitle("Register");
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

        Color labColor = new Color(153, 183, 111);
        Font labFont = new Font("幼圆", Font.BOLD, 15);
        JLabel nameLabel = new JLabel("姓名");
        nameLabel.setFont(labFont);
        nameLabel.setBounds(this.getWidth() / 2 - 150, 250, 50, 30);
        nameLabel.setBackground(labColor);
        panel.add(nameLabel);

        JLabel phoneLabel = new JLabel("手机号");
        phoneLabel.setFont(labFont);
        phoneLabel.setBounds(this.getWidth() / 2 - 165, 300, 50, 30);
        phoneLabel.setBackground(labColor);
        panel.add(phoneLabel);

        Color txColor = new Color(207, 218, 210);
        JTextField nameField;
        nameField = new JTextField();
        nameField.setForeground(Color.BLACK);
        nameField.setBounds(this.getWidth() / 2 - 100, 250, 200, 30);
        nameField.setBackground(txColor);
        panel.add(nameField);
        nameField.setColumns(10);

        JTextField phoneField;
        phoneField = new JTextField();
        phoneField.setForeground(Color.BLACK);
        phoneField.setBounds(this.getWidth() / 2 - 100, 300, 200, 30);
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
        confirmButton.setBounds(this.getWidth() / 2 - 100, 400, 200, 30);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (phoneField.getText().length() != 11) {
                    JOptionPane.showMessageDialog(null, "手机号无效");
                } else if (nameField.getText().isEmpty() || phoneField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入信息");
                } else {
                    int isRegister = JOptionPane.showConfirmDialog(null, "是否确认注册？", "提示", JOptionPane.YES_NO_OPTION);
                    if (isRegister == JOptionPane.YES_OPTION) {
                        memberInfo = new MemberInfo(0, nameField.getText(), phoneField.getText());
                        if (MemberJdbc.select(memberInfo.getPhone())) {
                            JOptionPane.showMessageDialog(null, "此手机号已注册会员");
                        } else if (MemberJdbc.insert(memberInfo)) {
                            MemberJdbc.select(phoneField.getText());
                            JOptionPane.showMessageDialog(null, "注册成功！会员号："+memberInfo.getMembercode());
                            Cashier cashier = null;
                            try {
                                cashier = new Cashier();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            cashier.setVisible(true);
                            THIS.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "注册失败");
                        }
                    }
                }
            }
        });
        panel.add(confirmButton);
    }
}
