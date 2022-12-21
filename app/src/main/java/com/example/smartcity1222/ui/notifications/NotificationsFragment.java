package com.example.smartcity1222.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.smartcity1222.R;
import com.example.smartcity1222.activity.DontaiActivity;
import com.example.smartcity1222.activity.GuideActivity;
import com.example.smartcity1222.activity.SuiShouActivity;
import com.example.smartcity1222.activity.XianCeActivity;
import com.example.smartcity1222.ui.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends BaseFragment {
    private Banner myBanner;
    private LinearLayout dontai;
    private LinearLayout xuexi;
    private LinearLayout xiance;
    private LinearLayout suishoupai;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar("智慧党建",false);
        myBanner = (Banner) view.findViewById(R.id.myBanner);
        dontai = (LinearLayout) view.findViewById(R.id.dontai);
        xuexi = (LinearLayout) view.findViewById(R.id.xuexi);
        xiance = (LinearLayout) view.findViewById(R.id.xiance);
        suishoupai = (LinearLayout) view.findViewById(R.id.suishoupai);
        List<Integer> imgs = new ArrayList<>();
        imgs.add(R.drawable.dj1);
        imgs.add(R.drawable.dj2);
        imgs.add(R.drawable.dj3);
        myBanner.setAdapter(new BannerImageAdapter<Integer>(imgs) {
            @Override
            public void onBindView(BannerImageHolder holder, Integer integer, int i, int i1) {
                Glide.with(holder.imageView).load(imgs.get(i)).into(holder.imageView);
            }
        }, false).setIndicator(new CircleIndicator(getContext()));
        dontai.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), DontaiActivity.class));
        });
        xiance.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), XianCeActivity.class));
        });
        suishoupai.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SuiShouActivity.class));
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_notifications;
    }
}