package com.example.smartcity1222.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.DataI;
import com.example.smartcity1222.bean.GetInfo;
import com.example.smartcity1222.bean.ProfessionDta;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MineActivity extends BaseActivity {
    private ImageView avatar;
    private TextView personNum;
    private TextView nickName;
    private TextView email;
    private TextView phone;
    private TextView sex;
    private TextInputEditText exp;
    private TextInputEditText stuHistory;
    private TextInputEditText address;
    private Spinner expectProfession;
    private TextInputEditText salary;
    private TextInputEditText introduce;
    private TextInputEditText teacherHis;
    private TextInputEditText workHis;
    private MaterialButton btnAdd;
    private Map<String, Object> map;
    private List<String> strings;
    private ProfessionDta professionDta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("个人简历",true);
        initView();
    }

    private void initView() {
        strings = new ArrayList<>();
        avatar = (ImageView) findViewById(R.id.avatar);
        personNum = (TextView) findViewById(R.id.person_num);
        nickName = (TextView) findViewById(R.id.nick_name);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        sex = (TextView) findViewById(R.id.sex);
        exp = (TextInputEditText) findViewById(R.id.exp);
        stuHistory = (TextInputEditText) findViewById(R.id.stuHistory);
        address = (TextInputEditText) findViewById(R.id.address);
        expectProfession = (Spinner) findViewById(R.id.expectProfession);
        salary = (TextInputEditText) findViewById(R.id.salary);
        introduce = (TextInputEditText) findViewById(R.id.introduce);
        teacherHis = (TextInputEditText) findViewById(R.id.teacherHis);
        workHis = (TextInputEditText) findViewById(R.id.workHis);
        btnAdd = (MaterialButton) findViewById(R.id.btn_add);
        HttpUtil.doGet("/prod-api/api/common/user/getInfo", new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                GetInfo infoData = MyApplication.getGson().fromJson(response,GetInfo.class);
                avatar.post(() -> {
                    Glide.with(avatar).load(HttpUtil.host+infoData.getUser().getAvatar()).into(avatar);
                    personNum.setText("账号："+infoData.getUser().getUserName());
                    nickName.setText("昵称："+infoData.getUser().getNickName());
                    email.setText("邮箱："+infoData.getUser().getEmail());
                    phone.setText("手机："+infoData.getUser().getPhonenumber()+"");
                    if (infoData.getUser().getSex().equals("0")){
                        sex.setText("性别:男");
                    }else {
                        sex.setText("性别:女");
                    }
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
        HttpUtil.doGet("/prod-api/api/job/profession/list", new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                professionDta = MyApplication.getGson().fromJson(response, ProfessionDta.class);
                for (int i = 0; i < professionDta.getRows().size(); i++) {
                    strings.add(professionDta.getRows().get(i).getProfessionName());
                }
                email.post(() -> {
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(MineActivity.this,R.layout.layout,strings);
                    expectProfession.setAdapter(stringArrayAdapter);
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
        HttpUtil.doGet("/prod-api/api/job/resume/queryResumeByUserId/1112564",new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                DataI data = MyApplication.getGson().fromJson(response,DataI.class);
                exp.post(() -> {
                    exp.setText(data.getData().getExperience());
                    stuHistory.setText(data.getData().getMostEducation());
                    address.setText(data.getData().getAddress());
                    salary.setText(data.getData().getMoney());
                    introduce.setText(data.getData().getIndividualResume());
                    teacherHis.setText(data.getData().getEducation());
                    workHis.setText(data.getData().getExperience());
                    map = new HashMap<>();
                    for (int i = 0; i < strings.size(); i++) {
                        if (strings.get(i).equals(data.getData().getPositionName())){
                            expectProfession.setSelection(i);
                            break;
                        }else {
                            expectProfession.setSelection(0);
                        }
                    }

                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
        btnAdd.setOnClickListener(v -> {
            map.put("experience",exp.getText().toString());
            map.put("mostEducation",stuHistory.getText().toString());
            map.put("address",address.getText().toString());
            for (int i = 0; i < professionDta.getRows().size(); i++) {
                if(professionDta.getRows().get(i).getProfessionName().equals(expectProfession.getSelectedItem().toString())){
                    map.put("positionId",professionDta.getRows().get(i).getId());
                }
            }
            map.put("id",108);
            map.put("money",salary.getText().toString());
            map.put("individualResume",introduce.getText().toString());
            map.put("education",teacherHis.getText().toString());
            map.put("experience",exp.getText().toString());
            HttpUtil.doPut("/prod-api/api/job/resume", map, new HttpBack() {
                @Override
                public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                    btnAdd.post(() -> {
                        Toast.makeText(MineActivity.this,"保存成功!",Toast.LENGTH_SHORT).show();
                    });
                }

                @Override
                public void onFail(JSONObject jsonObject, String response) throws Exception {

                }
            });
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_mine;
    }
}