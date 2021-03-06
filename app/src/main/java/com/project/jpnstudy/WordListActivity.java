package com.project.jpnstudy;

import android.content.Intent;
import android.content.res.TypedArray;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class WordListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Toolbar toolbar;

    static TextToSpeech tts;

    ArrayList<ListData> origin_list = new ArrayList<ListData>();
    ListData temp;
    private ListView listview;
    private ListViewAdapter adapter;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("단어목록");

        adapter = new ListViewAdapter(getApplicationContext(), R.id.word_list, origin_list);
        listview = (ListView) findViewById(R.id.word_list);

        setData();

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(this);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.JAPANESE);
                }
            }
        });
    }

    public void onItemClick (AdapterView<?> parent, View v, int position, long id) {
        ListData data = origin_list.get(position);

        Intent intent = new Intent(this, Word.class);
        intent.putExtra("number", position);
        startActivity(intent);
    }

    private void setData() {
        databaseReference.child("Word").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               adapter.clear();

               Integer rn = 1;
               while (dataSnapshot.child(rn.toString()).exists()) {
                   String word = dataSnapshot.child(rn.toString()).child("Name").getValue(String.class);
                   //Log.v("단어", word);
                   String meaning = dataSnapshot.child(rn.toString()).child("Meaning").getValue(String.class);
                   //Log.v("뜻", meaning);

                   temp = new ListData(word, meaning);
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
    protected void onDestroy() {
        super.onDestroy();

        if(tts !=null){
            tts.stop();
            tts.shutdown();
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
