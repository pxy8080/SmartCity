package com.example.smartcity1222.activity;

import android.os.Bundle;

import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class FeedActivity extends BaseActivity {
    private TextInputEditText feedback;
    private MaterialButton submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("意见反馈",true);
        feedback = (TextInputEditText) findViewById(R.id.feedback);
        submit = (MaterialButton) findViewById(R.id.submit);
        submit.setOnClickListener(v -> {
            if (feedback.getText().toString().equals("")||feedback.getText().toString().length()>150){
                MyApplication.showToast("请正确填写");
            }else {
                MyApplication.showToast("提交成功");
                feedback.setText("");
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_feed;
    }
}