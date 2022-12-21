package com.example.smartcity1222.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.activity.FindJobActivity;
import com.example.smartcity1222.activity.NewsDetailActivity;
import com.example.smartcity1222.activity.SearchListActivity;
import com.example.smartcity1222.activity.YicheDuCheActivity;
import com.example.smartcity1222.bean.AppData;
import com.example.smartcity1222.bean.IndexBanner;
import com.example.smartcity1222.bean.NewsData;
import com.example.smartcity1222.bean.NewsType;
import com.example.smartcity1222.bean.SingleNews;
import com.example.smartcity1222.ui.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends BaseFragment {
    private SearchView mySearch;
    private Banner myBanner;
    private GridView myApp;
    private TabLayout myTab;
    private RecyclerView myNews;
    private AppData appData;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar("首页", false);
        initView(view);
        initSearch();
        initBanner();
        initApp();
        initTab();
        myApp.setOnItemClickListener((parent, view1, position, id) -> {
            if (appData.getRows().get(position).getServiceName().equals("全部服务")){
               NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
               navController.setGraph(R.navigation.mobile_navigation);
               navController.navigate(R.id.navigation_dashboard);
            }else if (appData.getRows().get(position).getServiceName().equals("移车堵车")){
                startActivity(new Intent(getContext(), YicheDuCheActivity.class));
            }else if (appData.getRows().get(position).getServiceName().equals("找工作")){
                startActivity(new Intent(getContext(), FindJobActivity.class));
            }
        });
    }

    private void initTab() {
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
                            myNews.setLayoutManager(new LinearLayoutManager(getContext()));
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

    private void initApp() {
        HttpUtil.doGet("/prod-api/api/service/list?pageNum=1&pageSize=7", new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                myApp.post(() -> {
                    appData = MyApplication.getGson().fromJson(response, AppData.class);
                    appData.getRows().add(new AppData.RowsBean("全部服务",R.drawable.ic_dashboard_black_24dp));
                    appData.getRows().add(new AppData.RowsBean("移车堵车",R.drawable.car));
                    appData.getRows().add(new AppData.RowsBean("找工作",R.drawable.ic_baseline_work_outline_24));
                    myApp.setAdapter(new MyApp(appData.getRows()));
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
    }

    private void initSearch() {
        mySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getContext(), SearchListActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initBanner() {
        HttpUtil.doGet("/prod-api/api/rotation/list?type=2", new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                myBanner.post(() -> {
                    IndexBanner indexBanner = MyApplication.getGson().fromJson(response, IndexBanner.class);
                    myBanner.setAdapter(new BannerImageAdapter<IndexBanner.RowsBean>(indexBanner.getRows()) {
                        @Override
                        public void onBindView(BannerImageHolder holder, IndexBanner.RowsBean rowsBean, int i, int i1) {
                            Glide.with(holder.imageView).load(HttpUtil.host + indexBanner.getRows().get(i).getAdvImg()).apply(MyApplication.getRequestOptions()).into(holder.imageView);
                            holder.itemView.setOnClickListener(v -> {
                                HttpUtil.doGet("/prod-api/press/press/" + indexBanner.getRows().get(i).getTargetId(), new HttpBack() {
                                    @Override
                                    public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                                        myBanner.post(() -> {
                                            SingleNews newsData = MyApplication.getGson().fromJson(response, SingleNews.class);
                                            Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                                            intent.putExtra("title", newsData.getData().getTitle());
                                            intent.putExtra("img", newsData.getData().getCover());
                                            intent.putExtra("content", newsData.getData().getContent());
                                            startActivity(intent);
                                        });
                                    }

                                    @Override
                                    public void onFail(JSONObject jsonObject, String response) throws Exception {

                                    }
                                });
                            });
                        }
                    });
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
    }

    private void initView(View view) {
        mySearch = (SearchView) view.findViewById(R.id.mySearch);
        myBanner = (Banner) view.findViewById(R.id.myBanner);
        myApp = (GridView) view.findViewById(R.id.myApp);
        myTab = (TabLayout) view.findViewById(R.id.myTab);
        myNews = (RecyclerView) view.findViewById(R.id.myNews);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    class MyApp extends BaseAdapter {
        List<AppData.RowsBean> rows;
        private ImageView img;
        private TextView title;

        public MyApp(List<AppData.RowsBean> rows) {
            this.rows = rows;
        }

        @Override
        public int getCount() {
            return rows.size();
        }

        @Override
        public Object getItem(int position) {
            return rows.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            @SuppressLint("ViewHolder") View view = View.inflate(getContext(), R.layout.app, null);
            img = (ImageView) view.findViewById(R.id.img);
            title = (TextView) view.findViewById(R.id.title);
            if (rows.get(position).getImgUrl()==null){
                Glide.with(img).load(rows.get(position).getImg()).into(img);
            }else {
                Glide.with(img).load(HttpUtil.host + rows.get(position).getImgUrl()).into(img);
            }
            title.setText(rows.get(position).getServiceName());
            return view;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        List<NewsData.RowsBean> rows;

        public NewsAdapter(List<NewsData.RowsBean> rows) {
            this.rows = rows;
        }

        @NonNull
        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.news, parent, false);
            return new NewsAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
            holder.title.setText(rows.get(position).getTitle());
            new Thread(() -> {
                Spanned spanned = Html.fromHtml(rows.get(position).getContent(), source -> {
                    Drawable drawable = null;
                    try {
                        drawable = Glide.with(getContext()).load(HttpUtil.host + source).submit().get();
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