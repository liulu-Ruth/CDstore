package com.company;

import java.sql.*;
import java.text.SimpleDateFormat;

public class LeaseJdbc {
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
            e1.printStackTrace();
        }try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
			e.printStackTrace();
        }
        return connection;
    }
    static boolean insert(LeaseInfo leaseInfo) {
        Connection connection = getConn();
        String sql = "insert into leaseinfo( cdbarcode, number,name,phone,rent,deposit,rentaldate,returndate) values(?, ?, ?, ?,?,?,?,?)";
        PreparedStatement preparedStatement;//预编译
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);

            preparedStatement.setString(1, leaseInfo.getCdbarcode());
            preparedStatement.setInt(2, leaseInfo.getNumber());
            preparedStatement.setString(3, leaseInfo.getName());
            preparedStatement.setString(4,leaseInfo.getPhone());
            preparedStatement.setDouble(5,leaseInfo.getRent());
            preparedStatement.setDouble(6,leaseInfo.getDeposit());
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("LeaseJdbc Date.valueOf(sd.format(leaseInfo.getRentaldate())):"+sd.format(leaseInfo.getRentaldate()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(sd.format(leaseInfo.getRentaldate())));
            preparedStatement.setDate(8, Date.valueOf(sd1.format(leaseInfo.getReturndate())));

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

    static ResultSet selectall(String phone) {
        Connection connection = getConn();
        String sql = "select * from leaseinfo where phone= '" + phone + "';";
        PreparedStatement preparedStatement;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            int col = resultSet.getMetaData().getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    static boolean select(LeaseInfo leaseInfo) {

        Connection connection = getConn();
        String sql = "select * from leaseinfo where phone= '"+ leaseInfo.getPhone()+ "';" ;
        PreparedStatement preparedStatement;
        System.out.println(sql);

        try {
            System.out.println(sql);
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            System.out.println(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int col = resultSet.getMetaData().getColumnCount();
            System.out.println(col);
            if (resultSet.next()) {
                System.out.println("=========" + resultSet.getString(1) + "===========" + resultSet.getString(3) );

                /*leaseInfo.setCdbarcode();
                user.setWorktype(resultSet.getString(3));
                user.setName(resultSet.getString(4));

                Main.user.setWorktype(resultSet.getString(3));
                Main.user.setName(resultSet.getString(4));*/
                preparedStatement.close();
                connection.close();

                return true;
            }
            else {
                preparedStatement.close();
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }
}
