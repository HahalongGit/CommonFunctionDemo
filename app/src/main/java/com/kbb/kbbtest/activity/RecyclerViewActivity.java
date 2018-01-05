package com.kbb.kbbtest.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.adapter.InfoAdapter;
import com.kbb.kbbtest.adapter.TestAdapter;
import com.kbb.kbbtest.bean.ProductInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<ProductInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        for(int i=1;i<=20;i++){
            ProductInfo productInfo = new ProductInfo();
            productInfo.setName("新de洗面奶"+i);
            productInfo.setProductInfo("男士de用去油控逗系列"+i);
            list.add(productInfo);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        InfoAdapter infoAdapter = new InfoAdapter(list,this);
        //TestAdapter testAdapter = new TestAdapter(this,list);
        recyclerView.setAdapter(infoAdapter);

        infoAdapter.setOnItemClickListener(new InfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Toast.makeText(RecyclerViewActivity.this, "点击"+pos, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
