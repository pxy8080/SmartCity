package com.example.smartcity1222.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MainActivity;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.LoginData;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {
    private TextInputEditText account;
    private TextInputEditText password;
    private MaterialButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MyApplication.getSharedPreferences().getString("token", "").equals("")) {
            initToolbar("登录", false);
            account = (TextInputEditText) findViewById(R.id.account);
            password = (TextInputEditText) findViewById(R.id.password);
            login = (MaterialButton) findViewById(R.id.login);
            login.setOnClickListener(v -> {
                if (account.getText().toString().equals("") || password.getText().toString().equals("")) {
                    MyApplication.showToast("账号密码不能为空");
                } else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("username", account.getText().toString());
//                    map.put("password", password.getText().toString());
//                    HttpUtil.doPost("/prod-api/api/login", map, new HttpBack() {
//                        @Override
//                        public void onSuccess(JSONObject jsonObject, String response) throws Exception {
//                            login.post(() -> {
//                                try {
//                                    MyApplication.showToast(jsonObject.getString("msg"));
//                                    LoginData loginData = MyApplication.getGson().fromJson(response, LoginData.class);
//                                    MyApplication.getSharedPreferences().edit().putString("token", loginData.getToken()).apply();
//                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onFail(JSONObject jsonObject, String response) throws Exception {
//                            login.post(() -> {
//                                try {
//                                    MyApplication.showToast(jsonObject.getString("msg"));
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            });
//                        }
//                    });
                }
            });
        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }
}