package com.company;


public class MemberInfo {
    private int membercode;
    private String name;
    private String phone;

    public MemberInfo(int membercode,String name,String phone){
        this.membercode=membercode;
        this.name=name;
        this.phone=phone;
    }

    public int getMembercode() {
        return membercode;
    }

    public void setMembercode(int membercode) {
        this.membercode = membercode;
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
}
