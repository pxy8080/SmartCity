package com.example.smartcity1222.ui.dashboard;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smartcity1222.R;
import com.example.smartcity1222.ui.BaseFragment;

public class DashboardFragment extends BaseFragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar("全部服务",false);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_dashboard;
    }
}