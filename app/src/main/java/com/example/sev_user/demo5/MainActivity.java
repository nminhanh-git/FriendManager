package com.example.sev_user.demo5;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        MyDB mDbHelper = new MyDB(this);


    }

    public void initWidget() {
        mToolbar = (Toolbar) findViewById(R.id.tb_top);

        mToolbar.setTitle("FRIEND");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorTitleText));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_favorite_clicked);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_normal_clicked);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_blacklist_clicked);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                i.putExtra("command", "create");
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
