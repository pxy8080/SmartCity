package com.example.smartcity1222.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.FileData;
import com.example.smartcity1222.bean.GetInfo;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class InfoActivity extends BaseActivity {
    private ImageView img;
    private TextInputEditText nickName;
    private TextInputEditText phone;
    private RadioGroup group;
    private MaterialRadioButton male;
    private MaterialRadioButton female;
    private MaterialButton protect;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("个人信息",true);
        img = (ImageView) findViewById(R.id.img);
        nickName = (TextInputEditText) findViewById(R.id.nickName);
        phone = (TextInputEditText) findViewById(R.id.phone);
        group = (RadioGroup) findViewById(R.id.group);
        male = (MaterialRadioButton) findViewById(R.id.male);
        female = (MaterialRadioButton) findViewById(R.id.female);
        protect = (MaterialButton) findViewById(R.id.protect);
        HttpUtil.doGet("/prod-api/api/common/user/getInfo", new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                img.post(() -> {
                    GetInfo getInfo = MyApplication.getGson().fromJson(response,GetInfo.class);
                    Glide.with(img).load(HttpUtil.host+getInfo.getUser().getAvatar()).apply(MyApplication.getRequestOptions()).into(img);
                    nickName.setText(getInfo.getUser().getNickName());
                    phone.setText(getInfo.getUser().getPhonenumber());
                    if (getInfo.getUser().getSex().equals("0")){
                        male.setChecked(true);
                        female.setChecked(false);
                    }else {
                        male.setChecked(false);
                        female.setChecked(true);
                    }
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
        img.setOnClickListener(v -> {
            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),7);
        });
        protect.setOnClickListener(v -> {
            Map<String,Object> map = new HashMap<>();
            map.put("avatar",url);
            map.put("nickName",nickName.getText().toString());
            map.put("phonenumber",phone.getText().toString());
            if (male.isChecked()){
                map.put("sex","0");
            }else {
                map.put("sex","1");
            }
            HttpUtil.doPut("/prod-api/api/common/user", map, new HttpBack() {
                @Override
                public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                    protect.post(() -> {
                        try {
                            MyApplication.showToast(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                public void onFail(JSONObject jsonObject, String response) throws Exception {
                    protect.post(() -> {
                        try {
                            MyApplication.showToast(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==7&&resultCode==RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
            try {
                File file = new File(getExternalCacheDir()+"lzy.jpg");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                HttpUtil.uploadFile("/prod-api/common/upload", file, new HttpBack() {
                    @Override
                    public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                        img.post(() -> {
                            FileData fileData = MyApplication.getGson().fromJson(response,FileData.class);
                            url = "/prod-api"+fileData.getFileName();
                        });
                    }

                    @Override
                    public void onFail(JSONObject jsonObject, String response) throws Exception {

                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_info;
    }
}