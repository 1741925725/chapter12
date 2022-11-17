package com.wangkang.chapter12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wangkang.chapter12.adapter.ViewPageAdapter;
import com.wangkang.chapter12.fragment.IncomeFragment;
import com.wangkang.chapter12.fragment.OutlayFragment;
import com.wangkang.chapter12.fragment.SummaryFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dawer_layout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        //设置支持操作栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //显示操作栏返回图标
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        //给ViewPage添加三个页面
        viewPager = (ViewPager) findViewById(R.id.viewPage);
        setupViewPager(viewPager);

        //给底部按钮绑定事件，用于点击切换页面
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_summary:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_income:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_outlay:
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        //
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.query:
                Toast.makeText(this, "query", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        SummaryFragment summaryFragment = new SummaryFragment();
        IncomeFragment incomeFragment = new IncomeFragment();
        OutlayFragment outlayFragment = new OutlayFragment();
        adapter.addFragment(summaryFragment);
        adapter.addFragment(incomeFragment);
        adapter.addFragment(outlayFragment);
        viewPager.setAdapter(adapter);
    }
}