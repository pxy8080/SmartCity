package com.example.smartcity1222.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.FileData;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SuiShouActivity extends BaseActivity {
    private TextInputEditText feedback;
    private ImageView paizhao;
    private MaterialButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("随手拍", true);
        feedback = (TextInputEditText) findViewById(R.id.feedback);
        paizhao = (ImageView) findViewById(R.id.paizhao);
        submit = (MaterialButton) findViewById(R.id.submit);
        submit.setOnClickListener(v -> {
            if (feedback.getText().toString().equals("")||feedback.getText().toString().length()>150){
                MyApplication.showToast("请正确填写");
            }else {
                MyApplication.showToast("提交成功");
                feedback.setText("");
            }
        });
        paizhao.setOnClickListener(v -> {
            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),7);
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==7&&resultCode==RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            paizhao.setImageBitmap(bitmap);
        }
    }
    @Override
    protected int setLayout() {
        return R.layout.activity_sui_shou;
    }
}