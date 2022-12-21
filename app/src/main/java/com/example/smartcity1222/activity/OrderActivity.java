package com.example.smartcity1222.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.OrderBean;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

import java.util.List;

public class OrderActivity extends BaseActivity {
    private TabLayout tab;
    private RecyclerView listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("订单",true);
        tab = (TabLayout) findViewById(R.id.tab);
        listItem = (RecyclerView) findViewById(R.id.list_item);
        tab.addTab(tab.newTab().setText("已支付").setTag("Y"));
        tab.addTab(tab.newTab().setText("未支付").setTag("N"));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tag = (String) tab.getTag();
                HttpUtil.doGet("/prod-api/api/movie/ticket/order/list?status=" + tag, new HttpBack() {
                    @Override
                    public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                        listItem.post(() -> {
                            OrderBean orderBean = MyApplication.getGson().fromJson(response,OrderBean.class);
                            listItem.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                            listItem.setAdapter(new OrderList(orderBean.getRows()));
                        });
                    }

                    @Override
                    public void onFail(JSONObject jsonObject, String response) throws Exception {

                    }
                });
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class OrderList extends RecyclerView.Adapter<OrderList.ViewHolder>{
        List<OrderBean.RowsBean> rows;
        public OrderList(List<OrderBean.RowsBean> rows) {
            this.rows = rows;
        }

        @NonNull
        @Override
        public OrderList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(OrderActivity.this).inflate(R.layout.order, parent, false);
            return new OrderList.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderList.ViewHolder holder, int position) {
            holder.orderNo.setText("订单号:"+rows.get(position).getOrderNo());
            holder.orderStatus.setText("是否支付:"+rows.get(position).getStatus());
            holder.time.setText("创建时间:"+rows.get(position).getCreateTime());
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(OrderActivity.this,OrderDetailActivity.class);
                intent.putExtra("orderNo",rows.get(position).getOrderNo());
                intent.putExtra("orderStatus","是否支付:"+rows.get(position).getStatus());
                intent.putExtra("time",rows.get(position).getCreateTime());
                intent.putExtra("type","电影");
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return rows.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView orderNo;
            private TextView orderStatus;
            private TextView time;

            public ViewHolder(@NonNull View view) {
                super(view);
                orderNo = (TextView) view.findViewById(R.id.orderNo);
                orderStatus = (TextView) view.findViewById(R.id.orderStatus);
                time = (TextView) view.findViewById(R.id.time);
            }
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_order;
    }
}