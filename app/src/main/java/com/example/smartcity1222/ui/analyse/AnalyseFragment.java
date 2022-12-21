package com.example.smartcity1222.ui.analyse;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.NewsData;
import com.example.smartcity1222.ui.BaseFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.json.JSONObject;

import java.util.ArrayList;

public class AnalyseFragment extends BaseFragment {
    private LineChart line;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar("数据分析", false);
        line = (LineChart) view.findViewById(R.id.line);
        HttpUtil.doGet("/prod-api/press/press/list?pageNum=1&pageSize=5", new HttpBack() {
            @Override
            public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                line.post(() -> {
                    NewsData newsData = MyApplication.getGson().fromJson(response, NewsData.class);
                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<Entry> yValue = new ArrayList<>();
                    for (int i = 0; i < newsData.getRows().size(); i++) {
                        titles.add(newsData.getRows().get(i).getTitle());
                        yValue.add(new Entry(i,newsData.getRows().get(i).getLikeNum()));
                    }
                    LineDataSet lineDataSet = new LineDataSet(yValue,"新闻点赞数");
                    LineData lineData = new LineData(lineDataSet);
                    XAxis xAxis = line.getXAxis();
                    xAxis.setLabelCount(4);
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    ValueFormatter valueFormatter = new ValueFormatter() {
                        @Override
                        public String getFormattedValue(float value) {
                            return titles.get((int)value).substring(0,4)+"...";
                        }
                    };
                    xAxis.setValueFormatter(valueFormatter);
                    line.getLegend().setEnabled(false);
                    line.setDescription(null);
                    line.setData(lineData);
                    line.invalidate();
                });
            }

            @Override
            public void onFail(JSONObject jsonObject, String response) throws Exception {

            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_analyse;
    }
}
