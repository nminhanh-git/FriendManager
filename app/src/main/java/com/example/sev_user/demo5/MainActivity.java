package com.example.sev_user.demo5;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
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
    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;

    private Fragment1 f1;
    private Fragment2 f2;
    private Fragment3 f3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setBackground(getResources().getDrawable(R.drawable.background_item));
                btn1.setImageResource(R.drawable.ic_tab_favorite_clicked);
                btn2.setBackgroundColor(Color.TRANSPARENT);
                btn2.setImageResource(R.drawable.ic_tab_normal);
                btn3.setBackgroundColor(Color.TRANSPARENT);
                btn3.setImageResource(R.drawable.ic_tab_blacklist);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frml, f1);
                ft.commit();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn2.setBackground(getResources().getDrawable(R.drawable.background_item));
                btn2.setImageResource(R.drawable.ic_tab_normal_clicked);
                btn1.setBackgroundColor(Color.TRANSPARENT);
                btn1.setImageResource(R.drawable.ic_tab_favorite);
                btn3.setBackgroundColor(Color.TRANSPARENT);
                btn3.setImageResource(R.drawable.ic_tab_blacklist);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frml, f2);
                ft.commit();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn3.setBackground(getResources().getDrawable(R.drawable.background_item));
                btn3.setImageResource(R.drawable.ic_tab_blacklist_clicked);
                btn2.setBackgroundColor(Color.TRANSPARENT);
                btn2.setImageResource(R.drawable.ic_tab_normal);
                btn1.setBackgroundColor(Color.TRANSPARENT);
                btn1.setImageResource(R.drawable.ic_tab_favorite);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frml, f3);
                ft.commit();
            }
        });

        MyDB mDbHelper = new MyDB(this);
    }

    public void initWidget() {
        mToolbar = (Toolbar) findViewById(R.id.tb_top);
        btn1 = (ImageButton) findViewById(R.id.btn_love);
        btn2 = (ImageButton) findViewById(R.id.btn_normal);
        btn3 = (ImageButton) findViewById(R.id.btn_black);
        btn1.setElevation(1);
        btn2.setElevation(1);
        btn3.setElevation(1);
        mToolbar.setTitle("FRIEND");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorTitleText));
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        f1 = new Fragment1();
        f2 = new Fragment2();
        f3 = new Fragment3();
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
