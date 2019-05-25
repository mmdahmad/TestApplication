package com.amd.mmd.myapplication.MVVM.ViewModel;

import android.content.Context;

public class UserViewModel {

    private String name;
    private String phone;

    private Context context;

    public UserViewModel(String name, String phone, Context context) {
        this.name = name;
        this.phone = phone;
        this.context = context;
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
