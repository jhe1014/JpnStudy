package com.project.jpnstudy;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

public class Favorites extends AppCompatActivity {

    Toolbar toolbar;

    private ListView listview;
    private ListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("즐겨찾기");
        adapter = new ListViewAdapter();
        listview = (ListView) findViewById(R.id.word_list);

        setData();

        listview.setAdapter(adapter);
    }


    private void setData() {
        String[] words = getResources().getStringArray(R.array.word);
        String[] meanings = getResources().getStringArray(R.array.meaning);
        TypedArray arrResstar = getResources().obtainTypedArray(R.array.resstar);
        TypedArray arrReshs = getResources().obtainTypedArray(R.array.reshs);

        for(int i = 0; i < arrResstar.length(); i++) {
            ListData listData = new ListData();
            listData.settWord(words[i]);
            listData.settMeaning(meanings[i]);
            listData.setStarIcon(arrResstar.getResourceId(i, 0));
            listData.setHeadsetIcon(arrReshs.getResourceId(i, 0));

            adapter.addItem(listData);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
