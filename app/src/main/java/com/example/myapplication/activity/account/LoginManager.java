package com.example.myapplication.activity.account;

import android.content.SharedPreferences;

import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.User;
import com.example.myapplication.model.resObj;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager {
    private final SharedPreferences sharedPreferences;

    public LoginManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveLogin(String id, Boolean isLogin, Boolean isManager) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.putBoolean("isLoggedIn", isLogin);
        editor.putBoolean("isManager", isManager);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }

    public boolean isManager() {
        return sharedPreferences.getBoolean("isManager", false);
    }
    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("token");
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
    }
}
