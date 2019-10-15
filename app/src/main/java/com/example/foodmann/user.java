package com.example.foodmann;

public class user {
    private String name;
    private String password;
    private String phone;
    public user(){

    }

    public user(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
