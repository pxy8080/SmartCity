package com.example.smartcity1222.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity1222.MyHelper;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.Data;
import com.example.smartcity1222.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity {
    List<Data> data = new ArrayList<>();
    private ListView listItem;
    TextView tieCountName;
    TextView tieTitle;
    TextView tieContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("历史记录",true);
        MyHelper myHelper = new MyHelper(HistoryActivity.this);
        SQLiteDatabase sqLiteDatabase = myHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from history",null);
        while (cursor.moveToNext()){
            data.add(new Data(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
        }
        cursor.close();
        sqLiteDatabase.close();
        listItem = findViewById(R.id.list_item);
        listItem.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = View.inflate(HistoryActivity.this,R.layout._content,null);
                tieCountName = view.findViewById(R.id.tie_countName);
                tieTitle = view.findViewById(R.id.tie_title);
                tieContent = view.findViewById(R.id.tie_content);
                tieCountName.setText("车牌"+data.get(position).getCarNum());
                tieTitle.setText("手机:"+data.get(position).getPhone());
                tieContent.setText("地址:"+data.get(position).getAddress());
                return view;
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_history;
    }
}