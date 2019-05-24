package com.company;

public class CDInfo {
    private String cdbarcode="";
    private String name="";
    private String category="";
    private double price=0.00;
    private int salestock=0;
    private int leasestock=-100;

    public CDInfo(){

    }

    public CDInfo(String cdbarcode,String name,String category,double price,int salestock,int leasestock){
        this.cdbarcode=cdbarcode;
        this.name=name;
        this.category=category;
        this.price=price;
        this.salestock=salestock;
        this.leasestock=leasestock;
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

    public int getSalestock() {
        return salestock;
    }

    public void setSalestock(int salestock) {
        this.salestock = salestock;
    }

    public int getLeasestock() {
        return leasestock;
    }

    public void setLeasestock(int leasestock) {
        this.leasestock = leasestock;
    }
}
