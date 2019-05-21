package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

    private JPanel contentPane;
    Main mainTHIS;
    public static User user;

    public static void main(String[] args) {
        // write your code here
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        mainTHIS = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        final Rectangle frameBounds = new Rectangle(
                screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        //setBounds(100 + 400, 100 + 10, 308, 500);
        setBounds(frameBounds);
        setTitle("CDstore");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        System.out.println("this.width:"+this.getWidth()+"   this.height:"+this.getHeight()+"   screenSize.width:"+screenSize.width
        +"   screenSize.height:"+screenSize.height);
        System.out.println("screenInsets.left:"+screenInsets.left+"   screenInsets.top:"+screenInsets.top);
        System.out.println("screenSize.width - screenInsets.left - screenInsets.right:"+(screenSize.width - screenInsets.left - screenInsets.right));
        System.out.println("screenSize.height - screenInsets.top - screenInsets.bottom:"+(screenSize.height - screenInsets.top - screenInsets.bottom));

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

        JPanel panel1 = new JPanel() {
            public void paintComponent(Graphics g) {
                //super.paintComponent(g);
                //  /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片
                /*Graphics2D g2d = (Graphics2D) g.create();

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.BLUE);
                g2d.drawRoundRect(30, 150, 80, 100, 30, 30);*/
                //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(Color.GRAY);
                g.drawRoundRect(0, 0, 300, 350, 30, 30);
                //g.fillRoundRect(30, 50, 80, 100, 30, 30);
            }
        };
        panel1.setBounds(this.getWidth()/2+150, this.getHeight()/4, this.getWidth()/2, this.getHeight()/3*2);
        panel.add(panel1);
        panel1.setLayout(null);
        panel1.setBackground(Color.WHITE);
        panel1.setOpaque(false);

        Font myFont=new Font("幼圆",Font.BOLD,20);
        Font hintFont=new Font("宋体",Font.PLAIN,18);
        Color laColor = new Color(207,218,210);

        /*JLabel lblNewLabel = new JLabel("账号");
        lblNewLabel.setFont(myFont);
        lblNewLabel.setBounds(this.getWidth()/2-150, this.getHeight()/2-100, 50, 30);
        lblNewLabel.setBackground(laColor);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("密码");
        lblNewLabel_1.setFont(myFont);
        lblNewLabel_1.setBounds(this.getWidth()/2-150, this.getHeight()/2, 50, 30);
        lblNewLabel_1.setBackground(laColor);
        panel.add(lblNewLabel_1);*/

        JTextField idField;
        JTextField passwordField;


        Color txColor = new Color(207,218,210);
        idField = new JTextField();
        idField.setForeground(Color.GRAY);
        idField.setFont(hintFont);
        idField.setText("请输入账号");
        idField.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(idField.getText().equals("请输入账号"))
                    idField.setText("");
            }
        });
        idField.setBounds(50, 90, 200, 30);
        idField.setBackground(txColor);
        panel1.add(idField);
        idField.setColumns(10);

        passwordField = new JTextField();
        passwordField.setForeground(Color.GRAY);
        passwordField.setFont(hintFont);
        passwordField.setText("请输入密码");
        passwordField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(passwordField.getText().equals("请输入密码"))
                    passwordField.setText("");}
        });
        passwordField.setBounds(50, 140, 200, 30);
        passwordField.setBackground(txColor);
        panel1.add(passwordField);
        passwordField.setColumns(10);


        Color btnColor = new Color(153, 183, 111);
        JButton btnNewButton = new JButton("登录");
        btnNewButton.setFont(myFont);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*mainTHIS.dispose();
                Login login = new Login();
                login.setVisible(true);*/
                String id = idField.getText();
                String password = passwordField.getText();
                System.out.println("id:"+id+" password:"+password);
                if(id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入用户名");
                }
                if(password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入密码");
                }

                // 这里jdbc编程验证用户

                user = new User( id, password, "","");
                System.out.println("id:"+user.getId()+" password:"+user.getPassword());

                if (StoreJdbc.select(user)) {
                    System.out.println(user.getWorktype());
                    if (user.getWorktype().equals("cashier")) {
                        System.out.println(user.getWorktype());
                        Cashier cashier = new Cashier();
                        cashier.setVisible(true);
                    }else if(user.getWorktype().equals("buyer")){
                        System.out.println(user.getWorktype());
                        Buyer buyer=new Buyer();
                        buyer.setVisible(true);
                    }
                    mainTHIS.dispose();
                } else {
                    JOptionPane.showMessageDialog(mainTHIS, "用户名或密码错误");
                }
            }
        });

        //btnNewButton.setBounds(this.getWidth()/2-100, this.getHeight()/2+150, 200, 30);
        btnNewButton.setBounds(50, 220, 200, 30);
        btnNewButton.setBackground(btnColor);
        panel1.add(btnNewButton);

        /*JButton btnNewButton_1 = new JButton("注册");
        btnNewButton_1.setFont(myFont);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTHIS.dispose();
                Register register = new Register();
                register.setVisible(true);

            }
        });
        btnNewButton_1.setBounds(50, 230, 200, 30);
        btnNewButton_1.setBackground(btnColor);
        panel1.add(btnNewButton_1);*/
    }
}