package com.example.smartcity1222.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.ExecutionException;

public class NewsDetailActivity extends BaseActivity {
    private ImageView img;
    private TextView content;
    private TextInputEditText account;
    private MaterialButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(getIntent().getStringExtra("title"), true);
        img = (ImageView) findViewById(R.id.img);
        content = (TextView) findViewById(R.id.content);
        account = (TextInputEditText) findViewById(R.id.account);
        login = (MaterialButton) findViewById(R.id.login);
        Glide.with(img).load(HttpUtil.host + getIntent().getStringExtra("img")).apply(MyApplication.getRequestOptions()).into(img);
        new Thread(() -> {
            Spanned spanned = Html.fromHtml(getIntent().getStringExtra("content"), source -> {
                Drawable drawable = null;
                try {
                    drawable = Glide.with(NewsDetailActivity.this).load(HttpUtil.host + source).submit().get();
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return drawable;
            }, null);
            content.post(() -> {
                content.setText(spanned);
            });
        }).start();
        login.setOnClickListener(v -> {
            if (account.getText().toString().equals("")){
                MyApplication.showToast("评论不能为空!");
            }else {
                account.setText("");
                MyApplication.showToast("评论成功!");
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_news_detail;
    }
}