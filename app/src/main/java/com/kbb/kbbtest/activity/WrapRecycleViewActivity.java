package com.kbb.kbbtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.adapter.RecycleViewAdapter;
import com.kbb.kbbtest.widget.WrapRecyclerAdapter;
import com.kbb.kbbtest.widget.WrapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WrapRecycleViewActivity extends AppCompatActivity {

    @BindView(R.id.wrap_recycleView)
    WrapRecyclerView wrapRecycleView;

    private LinearLayoutManager linearLayoutManager;

    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrap_recycle_view);
        ButterKnife.bind(this);
        for (int i = 0; i < 20; i++) {
            list.add("list:" + i);
        }
        linearLayoutManager = new LinearLayoutManager(this);
        wrapRecycleView.setLayoutManager(linearLayoutManager);
        // 实例化头部View
        // TODO: 2017/11/7  此处的 装饰着模式 适配器怎么设置
        View headerView = LayoutInflater.from(this).inflate(R.layout.recycle_wrap_head_layout, wrapRecycleView, false);
        // 设置适配器.此处的适配器怎么设置
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(this, list);
        wrapRecycleView.setAdapter(new WrapRecyclerAdapter(recycleViewAdapter));
        // 添加头部
        wrapRecycleView.addHeaderView(headerView);

        ListView listView = new ListView(this);
//        listView.addFooterView();
//        listView.setAdapter();
    }
}
