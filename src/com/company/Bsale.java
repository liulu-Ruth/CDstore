package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Bsale extends JFrame{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Bsale lFrame = new Bsale();
                    lFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private boolean visible;
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setVisible() {
    }
    private JPanel contentPane;
    Bsale THIS;

    public Bsale() {
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
        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//让contentPane内部边框为空，并且有5个像素的厚度，如果直接在contentPane上面添加一个按钮（设置为充满），那么按钮将铺满除了边框之外的内部矩形
        setContentPane(contentPane);//意思是给窗体加个pane
        contentPane.setLayout(null);

        JPanel panel=new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("pho\\qxdyjj.jpg"));

                Image img = icon.getImage();
                g.drawImage(img, 0, 0, screenSize.width, screenSize.height, this);

            }

        };
        panel.setBounds(0,0,this.getWidth(),this.getHeight());
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBounds(250, 150, this.getWidth() - 400, this.getHeight() - 300);
        panel1.setLayout(null);
        panel.add(panel1);


    }



}

