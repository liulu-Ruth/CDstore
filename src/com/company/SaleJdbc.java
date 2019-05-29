package com.company;

import java.sql.*;
import java.text.SimpleDateFormat;

public class SaleJdbc {
    static Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/oo?serverTimezone=GMT";

        String username = "root";
        String password = "root";
        Connection connection = null;
        try {
            connection = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
        }
        return connection;
    }

    static boolean insert(SaleInfo saleInfo) {
        Connection connection = getConn();
        String sql = "insert into saleinfo( cdbarcode,name,number,category,price,totalprice) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;//预编译
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);

            preparedStatement.setString(1, saleInfo.getCdbarcode());
            preparedStatement.setString(2, saleInfo.getName());
            preparedStatement.setInt(3, saleInfo.getNumber());
            preparedStatement.setString(4,saleInfo.getCategory());
            preparedStatement.setDouble(5,saleInfo.getPrice());
            preparedStatement.setDouble(6,saleInfo.getTotalprice());

            // 重要的一步
            int a = preparedStatement.executeUpdate();
            System.out.println("a:  "  + a);
            // 关闭
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
