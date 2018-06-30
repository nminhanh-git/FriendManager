package com.example.sev_user.demo5;

import java.util.Date;

/**
 * Created by sev_user on 6/29/2018.
 */
public class Friend {
    private int id;
    private String name;
    private String birthday;
    private String phone;
    private String email;
    private String address;
    private String avata;
    private int sFriend;

    public Friend() {
    }

    public Friend(int id, String name, String birthday, String phone, String email, String address, String avata, int sFriend) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.avata = avata;
        this.sFriend = sFriend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }

    public int getsFriend() {
        return sFriend;
    }

    public void setsFriend(int sFriend) {
        this.sFriend = sFriend;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", avata='" + avata + '\'' +
                ", sFriend=" + sFriend +
                '}';
    }
}