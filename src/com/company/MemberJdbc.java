package com.company;

import java.sql.*;

public class MemberJdbc {
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

    static boolean insert(MemberInfo memberInfo) {
        Connection connection = getConn();
        String sql = "insert into member(membercode, name, phone) values(?, ?, ?)";
        PreparedStatement preparedStatement;//预编译
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);

            preparedStatement.setInt(1, memberInfo.getMembercode());
            preparedStatement.setString(2, memberInfo.getName());
            preparedStatement.setString(3, memberInfo.getPhone());

            // 重要的一步
            int a = preparedStatement.executeUpdate();
            // 关闭
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    static boolean select(int memberCode) {

        Connection connection = getConn();
        String sql = "select * from member where membercode= '" + memberCode + "';";
        PreparedStatement preparedStatement;
        System.out.println(sql);

        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int col = resultSet.getMetaData().getColumnCount();
            System.out.println(col);
            if (resultSet.next()) {
                preparedStatement.close();
                connection.close();
                return true;
            } else {
                preparedStatement.close();
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean select(String phone) {

        Connection connection = getConn();
        String sql = "select * from member where phone= '" + phone + "';";
        PreparedStatement preparedStatement;
        System.out.println(sql);
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int col = resultSet.getMetaData().getColumnCount();
            System.out.println(col);
            if (resultSet.next()) {
                Register.memberInfo.setMembercode(resultSet.getInt(1));
                preparedStatement.close();
                connection.close();
                return true;
            } else {
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
