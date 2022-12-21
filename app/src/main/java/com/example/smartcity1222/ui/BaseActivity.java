package com.example.smartcity1222.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smartcity1222.R;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbarMain;
    private ImageView back;
    private TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
    }

    protected abstract int setLayout();

    protected void initToolbar(String text,Boolean isShow){
        toolbarMain = (Toolbar) findViewById(R.id.toolbar_main);
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        toolbarMain.setTitle("");
        title.setText(text);
        if (isShow){
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(v -> {
                finish();
            });
        }else {
            back.setVisibility(View.GONE);
        }
    }

    protected void hideToolbar(){
        toolbarMain = (Toolbar) findViewById(R.id.toolbar_main);
        toolbarMain.setVisibility(View.GONE);
    }
}
