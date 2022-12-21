package com.example.smartcity1222.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.smartcity1222.R;
import com.example.smartcity1222.ui.BaseActivity;

public class OrderDetailActivity extends BaseActivity {
    private TextView orderNo;
    private TextView orderStatus;
    private TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("订单详情", true);
        orderNo = (TextView) findViewById(R.id.orderNo);
        orderStatus = (TextView) findViewById(R.id.orderStatus);
        time = (TextView) findViewById(R.id.time);
        orderNo.setText(getIntent().getStringExtra("orderNo"));
        orderStatus.setText(getIntent().getStringExtra("orderStatus"));
        time.setText(getIntent().getStringExtra("time"));
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_order_detail;
    }
}