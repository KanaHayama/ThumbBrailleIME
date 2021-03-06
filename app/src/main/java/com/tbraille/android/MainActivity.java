package com.tbraille.android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listview;
    // 模拟数据
    private List<String> dataList = null;
    public static int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = new ArrayList<String>();
        dataList.add("Settings");
        dataList.add("About us");
        // Setting adapter
        ListAdapter adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, dataList);
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        // bind item click event
        listview.setOnItemClickListener(this);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // position is the position of which data you are clicking
        // setting
        if (position == 0) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        if (position == 1) {
            Intent intent = new Intent(MainActivity.this, Aboutus.class);
            startActivity(intent);
        }
//        Toast.makeText(MainActivity.this, "Click on" + position + "",0).show();
    }

}
