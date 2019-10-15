package com.example.foodmann;

public class vendor {
    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String pin;
    private String work;
    private String password;

    public vendor() {
    }

    public vendor(String fname, String lname, String phone, String email, String pin, String work,String password) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.pin = pin;
        this.work = work;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
