package com.example.smartcity1222.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.CompanyData;
import com.example.smartcity1222.bean.Data;
import com.example.smartcity1222.bean.DataI;
import com.example.smartcity1222.bean.JobDataI;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JobDetail extends BaseActivity {
    private TextView jobName;
    private TextView jobMoney;
    private TextView jobContent;
    private TextView jobAddress;
    private TextView companyDetail;
    private MaterialButton btnAdd;
    private Map<String, Object> map;
    private JobDataI jobData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("职位详情", true);
        initView();
        HttpUtil.doGet("/prod-api/api/job/post/" + getIntent().getStringExtra("id"), new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                jobData = MyApplication.getGson().fromJson(response, JobDataI.class);
                jobName.post(() -> {
                    jobName.setText(getIntent().getStringExtra("name"));
                    if (jobData.getData().getAddress() == null) {
                        jobAddress.setText("地址：无");
                    } else {
                        jobAddress.setText("地址：" + jobData.getData().getAddress().trim());
                    }
                    if (jobData.getData().getObligation() == null) {
                        jobContent.setText("岗位职责:无");
                    } else {
                        jobContent.setText("岗位职责:" + jobData.getData().getObligation().trim());
                    }
                    jobMoney.setText("薪资:"+jobData.getData().getSalary().trim());
                    HttpUtil.doGet("/prod-api/api/job/company/" + jobData.getData().getCompanyId(),new HttpBack() {
                        @Override
                        public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                            CompanyData companyData = MyApplication.getGson().fromJson(response, CompanyData.class);
                            companyDetail.post(() -> {
                                companyDetail.setText(companyData.getData().getIntroductory());
                            });

                        }

                        @Override
                        public void onFail(JSONObject jsonObject, String response) throws Exception {

                        }
                    });
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
        btnAdd.setOnClickListener(v -> {
            HttpUtil.doGet("/prod-api/api/job/resume/queryResumeByUserId/1112564",new HttpBack() {
                @Override
                public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                    DataI data = MyApplication.getGson().fromJson(response,DataI.class);
                    if (data.getData().getEducation().equals("")||data.getData().getExperience().equals("")){
                        NavController navController = Navigation.findNavController(JobDetail.this, R.id.nav_host_fragment);
                        navController.setGraph(R.navigation.mobile_navigation);
                        navController.navigate(R.id.navigation_notifications);
                    }else {
                        map = new HashMap<>();
                        map.put("companyId",jobData.getData().getCompanyId());
                        map.put("money",jobData.getData().getSalary());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date =new Date( System.currentTimeMillis());
                        String time = simpleDateFormat.format(date);
                        map.put("satrTime",time);
                        map.put("postId",getIntent().getStringExtra("id"));
                        map.put("postName",getIntent().getStringExtra("name"));
                        HttpUtil.doPost("/prod-api/api/job/deliver", map,  new HttpBack() {
                            @Override
                            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                                btnAdd.post(() -> {
                                    Toast.makeText(JobDetail.this,"操作成功!",Toast.LENGTH_SHORT).show();
                                });
                            }

                            @Override
                            public void onFail(JSONObject jsonObject, String response) throws Exception {

                            }
                        });
                    }
                }

                @Override
                public void onFail(JSONObject jsonObject, String response) throws Exception {

                }
            });
        });
    }

    private void initView() {
        jobName = findViewById(R.id.job_name);
        jobMoney = findViewById(R.id.job_money);
        jobContent = findViewById(R.id.job_content);
        jobAddress = findViewById(R.id.job_address);
        companyDetail = findViewById(R.id.company_detail);
        btnAdd = findViewById(R.id.btn_add);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_job_detail;
    }
}