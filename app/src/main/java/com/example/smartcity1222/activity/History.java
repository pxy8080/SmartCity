package com.example.smartcity1222.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.HisData;
import com.example.smartcity1222.ui.BaseActivity;

import org.json.JSONObject;

public class History extends BaseActivity {
    private ListView history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("历史记录", true);
        history = findViewById(R.id.history);
        HttpUtil.doGet("/prod-api/api/job/deliver/list?userId=1112564", new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                history.post(() -> {
                    HisData hisData = MyApplication.getGson().fromJson(response, HisData.class);
                    history.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                            return hisData.getRows().size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return hisData.getRows().get(position);
                        }

                        @Override
                        public long getItemId(int position) {
                            return position;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            TextView name;
                            TextView salary;
                            TextView time;
                            TextView jobName;
                            @SuppressLint("ViewHolder") View view1 = View.inflate(History.this, R.layout.layout2, null);
                            jobName = view1.findViewById(R.id.job_name);
                            name = view1.findViewById(R.id.name);
                            salary = view1.findViewById(R.id.salary);
                            time = view1.findViewById(R.id.time);
                            jobName.setText(hisData.getRows().get(position).getPostName());
                            name.setText("公司名:"+hisData.getRows().get(position).getCompanyName());
                            salary.setText("薪资:"+hisData.getRows().get(position).getMoney());
                            time.setText("投递日期:"+hisData.getRows().get(position).getSatrTime());
                            return view1;
                        }
                    });
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_history2;
    }
}