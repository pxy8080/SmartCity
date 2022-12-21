package com.example.smartcity1222.activity;

import android.app.AlertDialog;
import android.app.Dialog;
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
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.NewsData;
import com.example.smartcity1222.ui.BaseActivity;
import com.example.smartcity1222.ui.home.HomeFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class XianCeActivity extends BaseActivity {
    private TextInputEditText feedback;
    private MaterialButton submit;
    private MaterialButton check;
    public static List<Idea> idea = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("建言献策",true);
        feedback = (TextInputEditText) findViewById(R.id.feedback);
        submit = (MaterialButton) findViewById(R.id.submit);
        check = (MaterialButton) findViewById(R.id.check);
        submit.setOnClickListener(v -> {
            if (feedback.getText().toString().equals("")||feedback.getText().toString().length()>150){
                MyApplication.showToast("请正确填写");
            }else {
                MyApplication.showToast("提交成功");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                idea.add(new Idea(simpleDateFormat.format(date),feedback.getText().toString()));
                feedback.setText("");
            }
        });
        check.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(XianCeActivity.this);
            RecyclerView recyclerView = new RecyclerView(XianCeActivity.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(XianCeActivity.this));
            recyclerView.setAdapter(new XianCe(idea));
            dialog.setView(recyclerView);
            dialog.show();
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_xian_ce;
    }

    class XianCe extends RecyclerView.Adapter<XianCe.ViewHolder> {
        List<Idea> rows;
        public XianCe(List<Idea> rows) {
            this.rows = rows;
        }

        @NonNull
        @Override
        public XianCe.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(XianCeActivity.this).inflate(R.layout.order, parent, false);
            return new XianCe.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull XianCe.ViewHolder holder, int position) {
            holder.orderNo.setText("用户");
            holder.orderStatus.setText(rows.get(position).getContent());
            holder.time.setText(rows.get(position).getTime());
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

   public class Idea{
        String time;
        String content;

        public Idea(String time, String content) {
            this.time = time;
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}