package com.example.smartcity1222.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }

    protected abstract int setLayout();

    protected void initToolbar(String text,Boolean isShow){
        if (getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).initToolbar(text,isShow);
        }
    }

    protected void hideToolbar(){
       if (getActivity() instanceof BaseActivity){
           ((BaseActivity) getActivity()).hideToolbar();
       }
    }
}
