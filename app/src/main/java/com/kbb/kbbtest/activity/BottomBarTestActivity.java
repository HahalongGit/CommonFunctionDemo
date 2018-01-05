package com.kbb.kbbtest.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.kbb.kbbtest.R;
import com.kbb.kbbtest.fragment.BarFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomBarTestActivity extends AppCompatActivity {

    @BindView(R.id.bottomBar)
    BottomNavigationBar bottomBar;

    @BindView(R.id.container)
    FrameLayout container;

    @BindView(R.id.bottom_bar_max)
    BottomNavigationViewEx bottomBarMax;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @BindView(R.id.action_menu_view)
    ActionMenuView actionMenuView;

    private BarFragment fragment;

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar_test);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG","onClick:"+v.getId());
                finish();
            }
        });
        //设置是否显示标题
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getMenuInflater().inflate(R.menu.menu_bar,actionMenuView.getMenu());
        actionMenuView.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(BottomBarTestActivity.this, "点击：" + item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new BarFragment();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
        bottomBar1();

        bottomBarMax.enableAnimation(false);
        bottomBarMax.enableItemShiftingMode(false);
        bottomBarMax.enableShiftingMode(false);
        bottomBarMax.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Log.e("TAG", "onNavigationItemReselected:" + item.getTitle());
                //再一次点击当前菜单，类似微博的点击刷新
            }
        });
        bottomBarMax.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.e("TAG", "onNavigationItemSelected:" + item.getTitle());
                Log.e("TAG", "onNavigationItemReselected-id:" + item.getItemId());
                switch (item.getItemId()) {
                    case R.id.i_faceIndex: {
                        Toast.makeText(BottomBarTestActivity.this, "首页", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.i_action: {
                        Toast.makeText(BottomBarTestActivity.this, "活动", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.i_me: {
                        Toast.makeText(BottomBarTestActivity.this, "我的", Toast.LENGTH_SHORT).show();
                    }
                }
                item.setChecked(true);
                // fragment.setType("");
                return true;
            }
        });

    }

    private void bottomBar1() {
        bottomBar.addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "首页").setActiveColorResource(R.color.colorPrimary)).
                addItem(new BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "活动").setActiveColorResource(R.color.colorPrimary)).
                addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "我的").setActiveColorResource(R.color.colorPrimary)).
                initialise();
        bottomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.e("TAG", "onTabSelected:" + position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

}
