package com.company;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import static java.time.LocalDate.now;

public class LeaseJdbc {
    static Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/oo?serverTimezone=GMT%2B8";

        String username = "root";
        String password = "root";

        Connection connection = null;
        try {
            connection = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
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
            preparedStatement.setString(4, leaseInfo.getPhone());
            preparedStatement.setDouble(5, leaseInfo.getRent());
            preparedStatement.setDouble(6, leaseInfo.getDeposit());
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("LeaseJdbc Date.valueOf(sd.format(leaseInfo.getRentaldate())):" + sd.format(leaseInfo.getRentaldate()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(sd.format(leaseInfo.getRentaldate())));
            preparedStatement.setDate(8, Date.valueOf(sd1.format(leaseInfo.getReturndate())));

            // 重要的一步
            int a = preparedStatement.executeUpdate();
            System.out.println("a:  " + a);
            // 关闭
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static ResultSet selectall() {
        Connection connection = getConn();
        String sql = "select * from leaseinfo ;";
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

    static ResultSet selectName(String name) {
        Connection connection = getConn();
        String sql = "select * from leaseinfo where name= '" + name + "';";
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

    static ResultSet selectPhone(String phone) {
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
        String sql = "select * from leaseinfo where phone= '" + leaseInfo.getPhone() + "';";
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
                System.out.println("=========" + resultSet.getString(1) + "===========" + resultSet.getString(3));
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

    static boolean delete(int id) {
        Connection connection = getConn();
        String sql = "delete from leaseinfo where leaseid = ?";
        PreparedStatement preparedStatement;//预编译
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            // 重要的一步
            int a = preparedStatement.executeUpdate();
            System.out.println("a:  " + a);
            // 关闭
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ResultSet selectreturn() {
        Connection connection = getConn();
        String sql = "select * from leaseinfo where returndate = ?;";
        PreparedStatement preparedStatement;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 1);
            java.util.Date rdate = (java.util.Date) c.getTime();
            preparedStatement.setDate(1, Date.valueOf(sd1.format(rdate)));
            resultSet = preparedStatement.executeQuery();
            int col = resultSet.getMetaData().getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet selectover(){
        Connection connection = getConn();
        String sql = "select leaseid,rent,deposit,returndate from leaseinfo where returndate < now();";
        PreparedStatement preparedStatement;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            int col = resultSet.getMetaData().getColumnCount();
            int[] id = new int[100];
            int[] time = new int[100];
            int i = 0;
            double[] rent = new double[100];
            double[] deposit = new double[100];
            while (resultSet.next()) {
                id[i] = resultSet.getInt(1);
                rent[i] = resultSet.getDouble(2);
                deposit[i] = resultSet.getDouble(3);
                java.util.Date date=resultSet.getDate(4);
                java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                java.util.Date beginDate= format.parse(date.toString());
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String date1=new java.util.Date().toString();
                date1=sd.format(new java.util.Date());
                System.out.println(date1);
                java.util.Date endDate= format.parse(String.valueOf(date1));
                long day=(beginDate.getTime()-endDate.getTime())/(24*60*60*1000);
                System.out.println("相隔的天数="+day);
                updateover(deposit[i]+day,rent[i]-day,id[i]);
                differentDays(Date.valueOf(date.toString()),Date.valueOf(date1.toString()));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static boolean updateover(double deposit,double rent,int id) {
        Connection connection = getConn();
        String sql = "update leaseinfo set deposit = ? , rent = ? where leaseid=?;";
        PreparedStatement preparedStatement;
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
                preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
                preparedStatement.setDouble(1, deposit);
                preparedStatement.setDouble(2, rent);
                preparedStatement.setInt(3, id);

                //preparedStatement.setDouble(1,);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int differentDays(java.util.Date date1,java.util.Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
}
