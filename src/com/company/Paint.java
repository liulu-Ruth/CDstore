package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class Paint extends JFrame {

    private JPanel contentPane;
    private DefaultTableModel tableModel;
    private JTable jtable;
    private JTextField textfield1;
    private JTextField textfield2;

    //JTextArea text = new JTextArea();
    private JScrollPane jscrollpane = new JScrollPane();
    private JPanel jpanel = new JPanel();

    private JButton button1 = new JButton("添加");
    private JButton button2 = new JButton("删除");
    private JButton button3 = new JButton("修改");

    private Vector<String> dataTitle = new Vector<String>();//表格列名
    private Vector<Vector<String>> data = new Vector<Vector<String>>();//表格单元格内容

    public Paint() {

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        setTitle("维护表格模型");

        setVisible(true);
        setBounds(300, 300, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //添加表格列名
        dataTitle.add("编号");
        dataTitle.add("内容");

        //添加表格单元格内容
        for (int i = 1; i < 30; i++) {
            Vector<String> W = new Vector<String>();
            W.add("A" + i * 10);
            W.add("B" + i * 10);
            data.add(W);
        }


        //创建指定表格模型的表格,并设置表格排序器，表格的选择模式为单选

        tableModel = new DefaultTableModel(data, dataTitle);

        jtable = new JTable(tableModel);

        jtable.setRowSorter(new TableRowSorter<TableModel>(tableModel));

        jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        //为表格添加鼠标事件监听器(单击事件)

        jtable.addMouseListener(new MouseAdapter() {


            public void mouseClicked(MouseEvent e) {
                int selectRow = jtable.getSelectedRow();//获取鼠标选择行的索引值
                Object selectValues1 = jtable.getValueAt(selectRow, 0);//获取选择的单元格的内容
                Object selectValues2 = jtable.getValueAt(selectRow, 1);
                textfield1.setText(selectValues1.toString());//将单元格的内容显示在文本框中
                textfield2.setText(selectValues2.toString());
            }

        });



        jscrollpane.setViewportView(jtable);
        jscrollpane.setBounds(0,0,300,300);
        contentPane.add(jscrollpane, BorderLayout.NORTH);
        contentPane.add(jpanel, BorderLayout.SOUTH);

        jpanel.add(new JLabel("编号："));
        jpanel.add(textfield1 = new JTextField("显示的是编号", 10));
        jpanel.add(new JLabel("内容："));
        jpanel.add(textfield2 = new JTextField("显示的是内容", 10));

        JButton button=new JButton("ss");
        contentPane.add(button);

        //向表格中添加内容

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                String[] addData = {textfield1.getText(), textfield2.getText()};//获取文本框中的内容
                tableModel.addRow(addData);//将文本框中的内容添加到表格模型中的末尾一行
                int row = jtable.getRowCount() + 1;//获取表格的行数
                textfield1.setText("成功添加到第" + row + "行");
                textfield2.setText("成功添加到第" + row + "行");
            }

        });

        jpanel.add(button1);

        //删除选中的表格行的内容
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int row = jtable.getSelectedRow();// 获得被选中行的索引
                if (row != -1) {// 判断是否存在被选中行
                    tableModel.removeRow(row);//从表格模型中删除
                }
            }
        });
        jpanel.add(button2);

        //修改选中的表格行的内容
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = jtable.getSelectedRow();// 获得被选中行的索引
                if (row != -1) {// 判断是否存在被选中行
                    tableModel.setValueAt(textfield1.getText(), row, 0);// 修改表格模型当中的指定值
                    tableModel.setValueAt(textfield2.getText(), row, 1);// 修改表格模型当中的指定值
                }
            }
        });
        jpanel.add(button3);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Paint p = new Paint();
    }
}