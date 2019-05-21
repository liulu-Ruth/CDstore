package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cashier extends JFrame {

    private JPanel contentPane;
    Cashier THIS;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cashier cFrame = new Cashier();
                    cFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Cashier(){
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


        Color labColor = new Color(153, 183, 111);
        Font labFont=new Font("幼圆",Font.BOLD,15);
        JLabel idLabel = new JLabel( Main.user.getName()+"你好，欢迎登录");
        idLabel.setFont(labFont);
        idLabel.setBounds(this.getWidth()/2+400, 0, 300, 30);
        idLabel.setBackground(labColor);
        panel.add(idLabel);

        Color btnColor = new Color(153, 183, 111);
        Font btnFont=new Font("幼圆",Font.BOLD,15);
        JButton leaseButton = new JButton("出租");
        leaseButton.setBounds(this.getWidth()/2-100, this.getHeight()/2-100, 200, 30);
        leaseButton.setBackground(btnColor);
        leaseButton.setFont(btnFont);
        leaseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Lease lease = new Lease();
                lease.setVisible(true);
                THIS.dispose();
            }
        });
        panel.add(leaseButton);

        JButton saleButton = new JButton("销售");
        saleButton.setBounds(this.getWidth()/2-100, this.getHeight()/2-50, 200, 30);
        saleButton.setBackground(btnColor);
        saleButton.setFont(btnFont);
        panel.add(saleButton);

        JButton queryButton = new JButton("查询租赁信息");
        queryButton.setBounds(this.getWidth()/2-100, this.getHeight()/2, 200, 30);
        queryButton.setBackground(btnColor);
        queryButton.setFont(btnFont);
        panel.add(queryButton);

        JButton returnButton = new JButton("归还");
        returnButton.setBounds(this.getWidth()/2-100, this.getHeight()/2+50, 200, 30);
        returnButton.setBackground(btnColor);
        returnButton.setFont(btnFont);
        panel.add(returnButton);

        JButton registerButton = new JButton("注册会员");
        registerButton.setBounds(this.getWidth()/2-100, this.getHeight()/2+100, 200, 30);
        registerButton.setBackground(btnColor);
        registerButton.setFont(btnFont);
        panel.add(registerButton);


    }

}
