package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Buyer extends JFrame {

    private JPanel contentPane;
    Buyer THIS;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Buyer bFrame = new Buyer();
                    bFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Buyer(){
        THIS=this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        final Rectangle frameBounds = new Rectangle(
                screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        //setBounds(100 + 400, 100 + 10, 308, 500);
        setBounds(frameBounds);
        setTitle("Buyer");
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
        JLabel idLabel = new JLabel( Main.user.getName() +"你好，欢迎登录");
        idLabel.setFont(labFont);
        idLabel.setBounds(this.getWidth()/2+400, 0, 300, 30);
        idLabel.setBackground(labColor);
        panel.add(idLabel);

        Color btnColor = new Color(153, 183, 111);
        Font btnFont=new Font("幼圆",Font.BOLD,15);

        JButton saleButton = new JButton("租赁商品");
        saleButton.setBounds(this.getWidth()/2-100, this.getHeight()/2-50, 200, 30);
        saleButton.setBackground(btnColor);
        saleButton.setFont(btnFont);
        saleButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Bsale bsale = new Bsale();
                //bsale.setVisible();
                THIS.dispose();
            }
        });
        //panel.add(saleButton);

        JButton queryButton = new JButton("库存查询");
        queryButton.setBounds(this.getWidth()/2-100, this.getHeight()/2, 200, 30);
        queryButton.setBackground(btnColor);
        queryButton.setFont(btnFont);
        queryButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Stock stock = null;
                try {
                    stock = new Stock();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                stock.setVisible(true);
                THIS.dispose();
            }
        });
        panel.add(queryButton);

        JButton returnButton = new JButton("库存查询");
        returnButton.setBounds(this.getWidth()/2-100, this.getHeight()/2+50, 200, 30);
        returnButton.setBackground(btnColor);
        returnButton.setFont(btnFont);
        returnButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                /*Breturn breturn = new Breturn();
                breturn.setVisible();
                THIS.dispose();*/
            }
        });
        //panel.add(returnButton);

        JButton backButton = new JButton("注销");
        backButton.setFont(btnFont);
        backButton.setBackground(btnColor);
        backButton.setBounds(20, 20, 200, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main main =  new Main();
                main.setVisible(true);
                THIS.dispose();
            }
        });
        panel.add(backButton);

    }
}
