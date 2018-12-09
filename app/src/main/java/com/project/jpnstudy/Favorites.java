package com.project.jpnstudy;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {
    Toolbar toolbar;

    ArrayList<FavoritesListData> favorites_list = new ArrayList<FavoritesListData>();
    FavoritesListData temp;
    private ListView f_listview;
    private FavoritesAdapter adapter;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("즐겨찾기");

        adapter = new FavoritesAdapter(getApplicationContext(), R.id.favorite_list, favorites_list);
        f_listview = (ListView) findViewById(R.id.favorite_list);

        setData();

        f_listview.setAdapter(adapter);
    }

    private void setData() {
        databaseReference.child("Like").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();

                Integer rn = 1;
                while (dataSnapshot.child(rn.toString()).exists()) {
                    String word = dataSnapshot.child(rn.toString()).child("word").getValue(String.class);
                    //Log.v("단어", word);
                    String meaning = dataSnapshot.child(rn.toString()).child("meaning").getValue(String.class);
                    //Log.v("뜻", meaning);

                    temp = new FavoritesListData(word, meaning);
                    favorites_list.add(temp);
                    rn = rn + 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
