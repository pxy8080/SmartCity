package com.example.smartcity1222.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.NewsData;
import com.example.smartcity1222.bean.NewsType;
import com.example.smartcity1222.ui.BaseActivity;
import com.example.smartcity1222.ui.home.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DontaiActivity extends BaseActivity {
    private TabLayout myTab;
    private RecyclerView myNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("党建动态",true);
        myTab = (TabLayout) findViewById(R.id.myTab);
        myNews = (RecyclerView) findViewById(R.id.myNews);
        HttpUtil.doGet("/prod-api/press/category/list", new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                myTab.post(() -> {
                    NewsType newsType = MyApplication.getGson().fromJson(response, NewsType.class);
                    for (NewsType.DataBean rowsBean : newsType.getData()) {
                        myTab.addTab(myTab.newTab().setText(rowsBean.getName()).setTag(rowsBean.getId()));
                    }
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
        myTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tag = (int) tab.getTag();
                HttpUtil.doGet("/prod-api/press/press/list?type=" + tag, new HttpBack() {
                    @Override
                    public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                        myNews.post(() -> {
                            NewsData newsData = MyApplication.getGson().fromJson(response, NewsData.class);
                            myNews.setLayoutManager(new LinearLayoutManager(DontaiActivity.this));
                            myNews.setAdapter(new NewsAdapter(newsData.getRows()));
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

    @Override
    protected int setLayout() {
        return R.layout.activity_dontai;
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        List<NewsData.RowsBean> rows;

        public NewsAdapter(List<NewsData.RowsBean> rows) {
            this.rows = rows;
        }

        @NonNull
        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(DontaiActivity.this).inflate(R.layout.news, parent, false);
            return new NewsAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
            holder.title.setText(rows.get(position).getTitle());
            new Thread(() -> {
                Spanned spanned = Html.fromHtml(rows.get(position).getContent(), source -> {
                    Drawable drawable = null;
                    try {
                        drawable = Glide.with(DontaiActivity.this).load(HttpUtil.host + source).submit().get();
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return drawable;
                }, null);
                holder.content.post(() -> {
                    holder.content.setText(spanned.toString().trim());
                });
            }).start();
            holder.time.setText(rows.get(position).getCreateTime());
            holder.comment.setText(rows.get(position).getCommentNum() + "");
            Glide.with(holder.img).load(HttpUtil.host + rows.get(position).getCover()).apply(MyApplication.getRequestOptions()).into(holder.img);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(DontaiActivity.this, NewsDetailActivity.class);
                intent.putExtra("title", rows.get(position).getTitle());
                intent.putExtra("img", rows.get(position).getCover());
                intent.putExtra("content", rows.get(position).getContent());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return rows.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView img;
            private TextView title;
            private TextView content;
            private TextView time;
            private TextView comment;

            public ViewHolder(@NonNull View view) {
                super(view);
                img = (ImageView) view.findViewById(R.id.img);
                title = (TextView) view.findViewById(R.id.title);
                content = (TextView) view.findViewById(R.id.content);
                time = (TextView) view.findViewById(R.id.time);
                comment = (TextView) view.findViewById(R.id.comment);
            }
        }
    }
}