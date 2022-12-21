package com.example.smartcity1222.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PasswordActivity extends BaseActivity {
    private TextInputEditText newPassword;
    private TextInputEditText oldPassword;
    private MaterialButton change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("密码修改", true);
        newPassword = (TextInputEditText) findViewById(R.id.new_password);
        oldPassword = (TextInputEditText) findViewById(R.id.old_password);
        change = (MaterialButton) findViewById(R.id.change);
        change.setOnClickListener(v -> {
            if (newPassword.getText().toString().equals("") || oldPassword.getText().toString().equals("")) {
                MyApplication.showToast("信息填写不完全!");
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("newPassword", newPassword.getText().toString());
                map.put("oldPassword", oldPassword.getText().toString());
                HttpUtil.doPut("/prod-api/api/common/user/resetPwd", map, new HttpBack() {
                    @Override
                    public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                        change.post(() -> {
                            try {
                                MyApplication.showToast(jsonObject.getString("msg"));
                                MyApplication.getSharedPreferences().edit().remove("token").apply();
                                startActivity(new Intent(PasswordActivity.this, LoginActivity.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onFail(JSONObject jsonObject, String response) throws Exception {
                        change.post(() -> {
                            try {
                                MyApplication.showToast(jsonObject.getString("msg"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_password;
    }
}