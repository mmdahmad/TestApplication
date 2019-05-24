package com.amd.mmd.myapplication.webService;

import android.text.GetChars;

import com.amd.mmd.myapplication.model.User;
import com.amd.mmd.myapplication.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("posts")
//    Call<UserResponse> getUsers();
    Call<List<User>> getUsers();
}
