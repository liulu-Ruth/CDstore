package com.company;

import javafx.scene.chart.XYChart;

import java.util.Date;


public class LeaseInfo {
    private int leaseid=0;
    private String cdbarcode="";
    private int number=0;
    private String name="";
    private String phone="";
    private double rent=0.00;
    private double deposit=0.00;
    private Date rentaldate=null;
    private Date returndate=null;

    public LeaseInfo() {
    }
    public LeaseInfo(int leaseid,String cdbarcode,int number,String name,String phone,double rent,double deposit,Date rentaldate,Date returndate){
        this.leaseid=leaseid;
        this.cdbarcode=cdbarcode;
        this.number=number;
        this.name=name;
        this.phone=phone;
        this.rent=rent;
        this.deposit=deposit;
        this.rentaldate=rentaldate;
        this.returndate=returndate;
    }

    public int getLeaseid() {
        return leaseid;
    }

    public void setLeaseid(int leaseid) {
        this.leaseid = leaseid;
    }

    public String getCdbarcode() {
        return cdbarcode;
    }

    public void setCdbarcode(String cdbarcode) {
        this.cdbarcode = cdbarcode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public Date getRentaldate() {
        return rentaldate;
    }

    public void setRentaldate(Date rentaldate) {
        this.rentaldate = rentaldate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }
}
