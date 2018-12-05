package com.project.jpnstudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class YoutubeActivity extends AppCompatActivity {
    Toolbar toolbar;

    private ListView listview;
    private YoutubeListAdaptr adapter;
    private int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpage);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("유튜브");
        adapter = new YoutubeListAdaptr();
        listview = (ListView) findViewById(R.id.youtube_list);


        setData();

        listview.setAdapter(adapter);
    }

    private void setData() {
        String[] y_title = getResources().getStringArray(R.array.y_title);
        String[] y_about = getResources().getStringArray(R.array.y_data);

        for(int i = 0; i < j; i++) {
            YoutubeitesListData listData = new YoutubeitesListData();
            listData.sety_title(y_title[i]);
            listData.sety_data(y_about[i]);

            adapter.addItem(listData);
        }
    }





    public void goToURL(View v) {
        int id = v.getId();
        LinearLayout layout = (LinearLayout)findViewById(id);
        String tag = (String)layout.getTag();

        Intent it = new Intent(this, WebPage.class);
        it.putExtra("it_tag", tag);
        startActivity(it);
    }


/*
    public void goToabout(View v) {

    }
     Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("youtube");
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 툴바 뒤로가기 버튼
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    }


