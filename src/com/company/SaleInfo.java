package com.company;

import java.util.Date;

public class SaleInfo {
    private int leaseid=0;
    private String cdbarcode="";
    private String name="";
    private int number=0;
    private String category="";
    private double price=0.00;
    private double totalprice=0.00;

    public SaleInfo(int leaseid, String cdbarcode, String name, int number, String category, double price,double totalprice){
        this.leaseid=leaseid;
        this.cdbarcode=cdbarcode;
        this.name=name;
        this.number=number;
        this.category=category;
        this.price=price;
        this.totalprice=totalprice;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }
}
