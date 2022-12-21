package com.example.smartcity1222.ui.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.activity.FeedActivity;
import com.example.smartcity1222.activity.InfoActivity;
import com.example.smartcity1222.activity.LoginActivity;
import com.example.smartcity1222.activity.OrderActivity;
import com.example.smartcity1222.activity.PasswordActivity;
import com.example.smartcity1222.bean.GetInfo;
import com.example.smartcity1222.ui.BaseFragment;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

public class PersonFragment extends BaseFragment {
    private ImageView img;
    private TextView name;
    private TextView info;
    private TextView order;
    private TextView password;
    private TextView feedback;
    private MaterialButton logout;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar("个人中心",false);
        initView(view);
        HttpUtil.doGet("/prod-api/api/common/user/getInfo", new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                img.post(() -> {
                    GetInfo getInfo = MyApplication.getGson().fromJson(response,GetInfo.class);
                    Glide.with(img).load(HttpUtil.host+getInfo.getUser().getAvatar()).apply(MyApplication.getRequestOptions()).into(img);
                    name.setText(getInfo.getUser().getNickName());
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
        logout.setOnClickListener(v -> {
            MyApplication.getSharedPreferences().edit().remove("token").apply();
            startActivity(new Intent(getContext(), LoginActivity.class));
        });
        info.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), InfoActivity.class));
        });
        order.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), OrderActivity.class));
        });
        password.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), PasswordActivity.class));
        });
        feedback.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), FeedActivity.class));
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        HttpUtil.doGet("/prod-api/api/common/user/getInfo", new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                img.post(() -> {
                    GetInfo getInfo = MyApplication.getGson().fromJson(response,GetInfo.class);
                    Glide.with(img).load(HttpUtil.host+getInfo.getUser().getAvatar()).apply(MyApplication.getRequestOptions()).into(img);
                    name.setText(getInfo.getUser().getNickName());
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
    }

    private void initView(View view) {
        img = (ImageView) view.findViewById(R.id.img);
        name = (TextView) view.findViewById(R.id.name);
        info = (TextView) view.findViewById(R.id.info);
        order = (TextView) view.findViewById(R.id.order);
        password = (TextView) view.findViewById(R.id.password);
        feedback = (TextView) view.findViewById(R.id.feedback);
        logout = (MaterialButton) view.findViewById(R.id.logout);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_person;
    }
}
