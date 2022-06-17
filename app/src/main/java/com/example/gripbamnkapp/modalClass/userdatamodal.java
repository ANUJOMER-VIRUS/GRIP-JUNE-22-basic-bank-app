package com.example.gripbamnkapp.modalClass;

public class userdatamodal {
    private String Name;
    private String balance;
private String email;
private String phone;
private String bankname;
private String accountnum;
private String ifsc;
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public String getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBankname() {
        return bankname;
    }

    public String getAccountnum() {
        return accountnum;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public userdatamodal(String name, String balance, String email, String phone, String bankname, String accountnum, String ifsc) {
        Name = name;
        this.balance = balance;
        this.email = email;
        this.phone = phone;
        this.bankname = bankname;
        this.accountnum = accountnum;
        this.ifsc = ifsc;
    }
}
