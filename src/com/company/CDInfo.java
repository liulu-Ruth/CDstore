package com.company;

public class CDInfo {
    private String cdbarcode="";
    private String name="";
    private String category="";
    private double price=0.00;
    private int stock=0;
    private int flag=-100;

    public CDInfo(){

    }

    public CDInfo(String cdbarcode,String name,String category,double price,int stock,int flag){
        this.cdbarcode=cdbarcode;
        this.name=name;
        this.category=category;
        this.price=price;
        this.stock=stock;
        this.flag=flag;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
