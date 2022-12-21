package com.example.smartcity1222.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnPageChangeListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private Banner guide;
    private MaterialButton net;
    private MaterialButton login;
    private TextInputEditText ip;
    private TextInputEditText port;
    private MaterialButton protect;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MyApplication.getSharedPreferences().getString("isFirst","").equals("yes")){
            startActivity(new Intent(GuideActivity.this,LoginActivity.class));
        }else {
            setContentView(R.layout.activity_guide);
            guide = (Banner) findViewById(R.id.guide);
            net = (MaterialButton) findViewById(R.id.net);
            login = (MaterialButton) findViewById(R.id.login);
            final List<Integer> pic = new ArrayList<>();
            pic.add(R.drawable.yd1);
            pic.add(R.drawable.yd2);
            pic.add(R.drawable.yd3);
            pic.add(R.drawable.yd4);
            pic.add(R.drawable.yd5);
            login.setOnClickListener(v -> {
                if (count==0){
                    MyApplication.showToast("您还未设置ip端口或设置的端口无效");
                }else {
                    startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                }
            });
            net.setOnClickListener(v -> {
                View view = View.inflate(GuideActivity.this, R.layout.net, null);
                Dialog dialog = new AlertDialog.Builder(GuideActivity.this).setView(view).create();
                ip = (TextInputEditText) view.findViewById(R.id.ip);
                port = (TextInputEditText) view.findViewById(R.id.port);
                protect = (MaterialButton) view.findViewById(R.id.protect);
                protect.setOnClickListener(v1 -> {
                    if (ip.getText().toString().equals("") || port.getText().toString().equals("")) {
                        MyApplication.showToast("信息不能为空");
                    } else {
                        HttpUtil.doTest("http://" + ip.getText().toString() + ":" + port.getText().toString() + "/prod-api/api/rotation/list", new HttpBack() {
                            @Override
                            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                                ip.post(() -> {
                                    MyApplication.getSharedPreferences().edit().putString("ip",ip.getText().toString()).apply();
                                    MyApplication.getSharedPreferences().edit().putString("port",port.getText().toString()).apply();
                                    MyApplication.showToast("保存成功");
                                    count = 1;
                                    MyApplication.getSharedPreferences().edit().putString("isFirst","yes").apply();
                                    dialog.dismiss();
                                });
                            }

                            @Override
                            public void onFail(JSONObject jsonObject, String response) throws Exception {

                            }
                        });
                    }
                });
                dialog.show();
            });
            guide.setAdapter(new BannerImageAdapter<Integer>(pic) {
                @Override
                public void onBindView(BannerImageHolder holder, Integer integer, int i, int i1) {
                    Glide.with(holder.imageView).load(pic.get(i)).into(holder.imageView);
                }
            }, false).setIndicator(new CircleIndicator(GuideActivity.this));
            guide.addOnPageChangeListener(new OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    if (i == 4) {
                        net.setVisibility(View.VISIBLE);
                        login.setVisibility(View.VISIBLE);
                    } else {
                        net.setVisibility(View.GONE);
                        login.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }
    }
}