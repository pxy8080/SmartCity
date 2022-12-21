package com.example.smartcity1222.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.example.smartcity1222.ui.BaseActivity;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchListActivity extends BaseActivity {
    private RecyclerView listItem;
    private Spanned spanned;
    private ProgressBar load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("搜索结果", true);
        load = (ProgressBar) findViewById(R.id.load);
        listItem = (RecyclerView) findViewById(R.id.list_item);
        HttpUtil.doGet("/prod-api/press/press/list?title=" + getIntent().getStringExtra("query"), new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                listItem.post(() -> {
                    NewsData newsData = MyApplication.getGson().fromJson(response, NewsData.class);
                    listItem.setLayoutManager(new LinearLayoutManager(SearchListActivity.this));
                    listItem.setAdapter(new NewsAdapter(newsData.getRows()));
                    load.setVisibility(View.GONE);
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_search_list;
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        List<NewsData.RowsBean> rows;

        public NewsAdapter(List<NewsData.RowsBean> rows) {
            this.rows = rows;
        }

        @NonNull
        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(SearchListActivity.this).inflate(R.layout.news, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
            holder.title.setText(rows.get(position).getTitle());
            new Thread(() -> {
                spanned = Html.fromHtml(rows.get(position).getContent(), source -> {
                    Drawable drawable = null;
                    try {
                        drawable = Glide.with(SearchListActivity.this).load(HttpUtil.host + source).submit().get();
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return drawable;
                }, null);
            }).start();
            holder.content.post(() -> {
                holder.content.setText(spanned);
            });
            holder.time.setText(rows.get(position).getCreateTime());
            holder.comment.setText(rows.get(position).getCommentNum() + "");
            Glide.with(holder.img).load(HttpUtil.host + rows.get(position).getCover()).apply(MyApplication.getRequestOptions()).into(holder.img);
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