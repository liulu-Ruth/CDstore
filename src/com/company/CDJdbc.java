package com.company;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CDJdbc {
    static Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/oo?serverTimezone=GMT";

        String username = "root";
        String password = "root";

        Connection connection = null;
        try {
            connection = (Connection) DriverManager.getConnection(url,username,password);
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

    static boolean insert(CDInfo cdInfo) {
        Connection connection = getConn();
        String sql = "insert into cdinfo(cdbarcode, name, category, price, salestock, leasestock) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;//预编译
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);

            preparedStatement.setString(1, cdInfo.getCdbarcode());
            preparedStatement.setString(2, cdInfo.getName());
            preparedStatement.setString(3, cdInfo.getCategory());
            preparedStatement.setDouble(4, cdInfo.getPrice());
            preparedStatement.setInt(5,cdInfo.getSalestock());
            preparedStatement.setInt(6,cdInfo.getLeasestock());

            // 重要的一步
            int a = preparedStatement.executeUpdate();
            System.out.println("a:  "  + a);
            // 关闭
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {


            return false;
        }

        return true;
    }

    static boolean leaseUpdata(int num,String cdbarcode,int flag){
        //flag=1 add flag=0 reduce
        Connection connection = getConn();
        String sql="update cdinfo set leasestock=? where cdbarcode='"+cdbarcode+"';";
        System.out.println(sql);
        PreparedStatement preparedStatement;
        try {

            // 重要的一步
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            int stock=select_cdbarcode(cdbarcode);

            if (flag==0) {
                preparedStatement.setInt(1, (stock - num));
            }else if (flag==1){
                preparedStatement.setInt(1, (stock + num));
            }else {
                preparedStatement.setInt(1, (stock ));
            }
            //preparedStatement.setString(2,cdbarcode);
            preparedStatement.executeUpdate();
            System.out.println(sql);
            // 关闭
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean saleUpdata(int num, String cdbarcode) {
        Connection connection = getConn();
        String sql="update cdinfo set salestock=? where cdbarcode='"+cdbarcode+"';";
        System.out.println(sql);
        PreparedStatement preparedStatement;
        try {

            // 重要的一步
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            int stock=select_cdbarcode(cdbarcode);
            System.out.println("stock-num:"+(stock-num));
            System.out.println("num:"+num);

            preparedStatement.setInt(1,(stock-num));
            //preparedStatement.setString(2,cdbarcode);
            preparedStatement.executeUpdate();
            System.out.println(sql);
            // 关闭
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static int select_cdbarcode(String cdbarcode) {

        Connection connection = getConn();
        String sql = "select * from cdinfo where cdbarcode= '"+ cdbarcode +"';" ;
        PreparedStatement preparedStatement;
        System.out.println(sql);
        int stock=0;

        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int col = resultSet.getMetaData().getColumnCount();
            System.out.println(col);
            if (resultSet.next()) {
                stock=resultSet.getInt(6);
                System.out.println("stock=resultSet.getInt(6):"+stock);
                preparedStatement.close();
                connection.close();
                return stock;
            }
            else {
                preparedStatement.close();
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stock;
    }
    /*
     * 这里只返回查询成功与否就可以，顺便改变一下user的win和fail
     * */
    static boolean select(CDInfo cdInfo,String kind) {

        Connection connection = getConn();
        String sql = "select * from cdinfo where cdbarcode= '"+ cdInfo.getCdbarcode() +"';" ;
        PreparedStatement preparedStatement;
        System.out.println("cdbarcode:"+cdInfo.getCdbarcode()+" name:"+cdInfo.getName());
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

                cdInfo.setName(resultSet.getString(2));
                cdInfo.setCategory(resultSet.getString(3));
                cdInfo.setPrice(resultSet.getDouble(4));

                if (kind.equals("lease")) {
                    Lease.cdInfo.setName(resultSet.getString(2));
                    Lease.cdInfo.setCategory(resultSet.getString(3));
                    Lease.cdInfo.setPrice(resultSet.getDouble(4));
                    Lease.cdInfo.setSalestock(resultSet.getInt(5));
                    Lease.cdInfo.setLeasestock(resultSet.getInt(6));
                }
                else if (kind.equals("sale")){
                    Sale.cdInfo.setName(resultSet.getString(2));
                    Sale.cdInfo.setCategory(resultSet.getString(3));
                    Sale.cdInfo.setPrice(resultSet.getDouble(4));
                    Sale.cdInfo.setSalestock(resultSet.getInt(5));
                    Sale.cdInfo.setLeasestock(resultSet.getInt(6));
                }

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

    public static void main(String[] args) {

    }

    public static ResultSet selectstock() {
        Connection connection = getConn();
        String sql = "select * from cdinfo ;";
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

    public static ResultSet selectbarcode(String barcode) {
        Connection connection = getConn();
        String sql = "select * from cdinfo where cdbarcode='"+barcode+"';";
        PreparedStatement preparedStatement;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            //preparedStatement.setString(1,barcode);
            resultSet = preparedStatement.executeQuery();
            int col = resultSet.getMetaData().getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static boolean updata(CDInfo cdInfo) {
        Connection connection = getConn();
        String sql="update cdinfo set price=? , salestock=? , leasestock=? where cdbarcode='"+cdInfo.getCdbarcode()+"';";
        System.out.println(sql);
        PreparedStatement preparedStatement;
        try {

            // 重要的一步
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);

            System.out.println(cdInfo.getPrice()+" "+cdInfo.getSalestock()+" "+cdInfo.getLeasestock());
            preparedStatement.setDouble(1,cdInfo.getPrice());
            preparedStatement.setInt(2,cdInfo.getSalestock());
            preparedStatement.setInt(3,cdInfo.getLeasestock());
            //preparedStatement.setString(2,cdbarcode);
            preparedStatement.executeUpdate();
            System.out.println(sql);
            // 关闭
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public static ResultSet selectorder(int i) {
        Connection connection = getConn();
        String sql = "select * from cdinfo where salestock < ?;";
        PreparedStatement preparedStatement;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1,20);
            resultSet = preparedStatement.executeQuery();
            int col = resultSet.getMetaData().getColumnCount();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet selectorder1(int i) {
        Connection connection = getConn();
        String sql = "select * from cdinfo where salestock > 100 ;";
        PreparedStatement preparedStatement;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            //preparedStatement.setInt(1,100);
            resultSet = preparedStatement.executeQuery();
            int col = resultSet.getMetaData().getColumnCount();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
