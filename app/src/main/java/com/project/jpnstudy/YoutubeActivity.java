package com.project.jpnstudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YoutubeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    Toolbar toolbar;

    ArrayList<YoutubeListData> origin_list = new ArrayList<YoutubeListData>();
    YoutubeListData temp;
    private ListView listview;
    private YoutubeListAdapter adapter;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtubelist);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("유튜브");

        adapter = new YoutubeListAdapter(getApplicationContext(), R.id.youtube_list, origin_list);
        listview = (ListView) findViewById(R.id.youtube_list);

        setData();

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        YoutubeListData data = origin_list.get(position);

        Intent intent = new Intent(this, Video.class);
        intent.putExtra("number", position);
        startActivity(intent);
    }

    private void setData() {
        databaseReference.child("Url").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();

                Integer rn = 0;
                while (dataSnapshot.child(rn.toString()).exists()) {
                    String title = dataSnapshot.child(rn.toString()).child("text").getValue(String.class);
                    //Log.v("단어", word);
                    //v_key = dataSnapshot.child(rn.toString()).child("url").getValue(String.class);
                    //Log.v("뜻", meaning);

                    temp = new YoutubeListData(title);
                    origin_list.add(temp);
                    rn = rn + 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


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



