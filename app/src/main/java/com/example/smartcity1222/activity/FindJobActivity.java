package com.example.smartcity1222.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.JobData;
import com.example.smartcity1222.bean.ProfessionDta;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.button.MaterialButton;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FindJobActivity extends BaseActivity {
    private SearchView MySearch;
    private Banner MyBanner;
    private MaterialButton subHis;
    private MaterialButton mine;
    private GridView MyApp;
    private RecyclerView MyRecycle;
    private ProfessionDta professionDta;
    static List<JobData.RowsDTO> rowsDTOS = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("找工作",true);
        initView();
        initBanner();
        initApp();
        subHis.setOnClickListener(v -> {
            startActivity(new Intent(FindJobActivity.this,History.class));
        });
        mine.setOnClickListener(v -> {
            startActivity(new Intent(FindJobActivity.this,MineActivity.class));
        });
    }

    private void initApp() {
        HttpUtil.doGet("/prod-api/api/job/post/list",  new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                JobData jobData = MyApplication.getGson().fromJson(response, JobData.class);
                MyRecycle.post(() -> {
                    rowsDTOS.addAll(jobData.getRows());
                    MyRecycle.setLayoutManager(new LinearLayoutManager(FindJobActivity.this));
                    MyRecycle.setAdapter(new MyRecycleAdapter(rowsDTOS));
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
        HttpUtil.doGet("/prod-api/api/job/profession/list",new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                professionDta = MyApplication.getGson().fromJson(response, ProfessionDta.class);
                MyApp.post(() -> {
                    MyApp.setAdapter(new MyAppAdapter(professionDta));
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
        MyApp.setOnItemClickListener((parent, view1, position, id) -> {
            HttpUtil.doGet("/prod-api/api/job/post/list?professionId=" + professionDta.getRows().get(position).getId(), new HttpBack() {
                @Override
                public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                    JobData jobData = MyApplication.getGson().fromJson(response, JobData.class);
                    MyRecycle.post(() -> {
                        rowsDTOS.clear();
                        rowsDTOS.addAll(jobData.getRows());
                        MyRecycle.setLayoutManager(new LinearLayoutManager(FindJobActivity.this));
                        MyRecycle.setAdapter(new MyRecycleAdapter(rowsDTOS));
                    });
                }

                @Override
                public void onFail(JSONObject jsonObject, String response) throws Exception {

                }
            });
        });
        MySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                HttpUtil.doGet("/prod-api/api/job/post/list",  new HttpBack() {
                    @Override
                    public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                        JobData jobData = MyApplication.getGson().fromJson(response, JobData.class);
                        MyRecycle.post(() -> {
                            rowsDTOS.clear();
                            rowsDTOS.addAll(jobData.getRows());
                            List<JobData.RowsDTO> cache = new ArrayList<>();
                            for (int i = 0; i < rowsDTOS.size(); i++) {
                                if (rowsDTOS.get(i).getProfessionName() != null) {
                                    if (rowsDTOS.get(i).getProfessionName().contains(query)) {
                                        cache.add(rowsDTOS.get(i));
                                        MyRecycle.setLayoutManager(new LinearLayoutManager(FindJobActivity.this));
                                        MyRecycle.setAdapter(new MyRecycleAdapter(cache));
                                    }
                                }
                            }
                        });
                    }
                    @Override
                    public void onFail(JSONObject jsonObject, String response) throws Exception {

                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initBanner() {
        List<Integer> imgs = new ArrayList<>();
        imgs.add(R.drawable.lb1);
        imgs.add(R.drawable.lb2);
        imgs.add(R.drawable.lb3);
        imgs.add(R.drawable.lb4);
        imgs.add(R.drawable.lb5);
        imgs.add(R.drawable.lb6);
        imgs.add(R.drawable.lb7);
        MyBanner.setAdapter(new BannerImageAdapter(imgs) {
            @Override
            public void onBindView(Object o, Object o2, int i, int i1) {
                Glide.with(((BannerImageHolder) o).imageView).load(imgs.get(i)).into(((BannerImageHolder) o).imageView);
            }
        }).setIndicator(new CircleIndicator(FindJobActivity.this));
    }

    private void initView() {
        MySearch = (SearchView) findViewById(R.id.MySearch);
        MyBanner = (Banner) findViewById(R.id.MyBanner);
        subHis = (MaterialButton) findViewById(R.id.subHis);
        mine = (MaterialButton) findViewById(R.id.mine);
        MyApp = (GridView) findViewById(R.id.MyApp);
        MyRecycle = (RecyclerView) findViewById(R.id.MyRecycle);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_find_job;
    }

    class MyAppAdapter extends BaseAdapter {
        private TextView appName;
        ProfessionDta professionDta;
        public MyAppAdapter(ProfessionDta professionDta) {
            this.professionDta = professionDta;
        }

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int position) {
            return professionDta.getRows().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            @SuppressLint("ViewHolder") View view1 = View.inflate(FindJobActivity.this, R.layout.app, null);
            ImageView img;
            img = (ImageView) view1.findViewById(R.id.img);
            appName = view1.findViewById(R.id.title);
            img.setVisibility(View.GONE);
            appName.setText(professionDta.getRows().get(position).getProfessionName());
            return view1;
        }
    }

    class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.ViewHolder> {
        List<JobData.RowsDTO> rowsDTOS;
        public MyRecycleAdapter(List<JobData.RowsDTO> rowsDTOS) {
            this.rowsDTOS = rowsDTOS;
        }

        @NonNull
        @Override
        public MyRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(FindJobActivity.this).inflate(R.layout.job, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyRecycleAdapter.ViewHolder holder, int position) {
            if (rowsDTOS.get(position).getProfessionName() == null) {
                holder.jobName.setText("其他");
            } else {
                holder.jobName.setText(rowsDTOS.get(position).getProfessionName().trim());
            }
            if (rowsDTOS.get(position).getAddress() == null) {
                holder.jobAddress.setText("地址：无");
            } else {
                holder.jobAddress.setText("地址：" + rowsDTOS.get(position).getAddress().trim());
            }
            if (rowsDTOS.get(position).getObligation() == null) {
                holder.jobContent.setText("岗位职责:无");
            } else {
                holder.jobContent.setText("岗位职责:" + rowsDTOS.get(position).getObligation().trim());
            }
            holder.jobMoney.setText("薪资:"+rowsDTOS.get(position).getSalary().trim());
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(FindJobActivity.this, JobDetail.class);
                intent.putExtra("id",rowsDTOS.get(position).getId().toString());
                intent.putExtra("name",rowsDTOS.get(position).getProfessionName());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return rowsDTOS.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView jobName;
            private TextView jobMoney;
            private TextView jobContent;
            private TextView jobAddress;

            public ViewHolder(@NonNull View view) {
                super(view);
                jobName = view.findViewById(R.id.job_name);
                jobMoney = view.findViewById(R.id.job_money);
                jobContent = view.findViewById(R.id.job_content);
                jobAddress = view.findViewById(R.id.job_address);
            }
        }
    }
}
