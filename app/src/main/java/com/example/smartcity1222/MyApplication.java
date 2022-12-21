package com.example.smartcity1222;

import android.app.Application;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

public class MyApplication extends Application {
    public static Toast toast;
    public static Gson gson;
    public static MyApplication instance;
    public static SharedPreferences sharedPreferences;
    public static RequestOptions requestOptions;
    @Override
    public void onCreate() {
        super.onCreate();
        gson = new Gson();
        instance = this;
        requestOptions = new RequestOptions().placeholder(R.drawable.ic_baseline_person_24).error(R.drawable.ic_baseline_error_outline_24);
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
    }

    public static RequestOptions getRequestOptions() {
        return requestOptions;
    }

    public static void showToast(String content){
        if (toast ==null){
            toast = Toast.makeText(MyApplication.getInstance(),content,Toast.LENGTH_SHORT);
        }else {
            toast.setText(content);
        }
        toast.show();
    }

    public static Gson getGson() {
        return gson;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
