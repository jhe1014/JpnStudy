package com.project.jpnstudy;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Locale;

public class Word extends AppCompatActivity {
    Toolbar toolbar;

    Integer row_num;

    private TextToSpeech tts;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    TextView d_word;
    TextView d_meaning;
    TextView d_sentence1;
    TextView d_meaning1;
    TextView d_sentence2;
    TextView d_meaning2;
    ImageButton sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        setTitle("단어");

        Intent intent = getIntent();
        row_num = intent.getExtras().getInt("number");
        Log.v("행", row_num.toString());

        setData(row_num);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.JAPANESE);
                }
            }
        });

        sound = (ImageButton) findViewById(R.id.word_headset);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = d_word.getText().toString();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ttsGreater21(text);
                } else {
                    ttsUnder20(text);
                }

            }
        });

    }

    private void setData(Integer qn) {
        qn++;
        databaseReference.child("Word").child(qn.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Name").getValue(String.class);
                d_word = (TextView) findViewById(R.id.word_detail);
                d_word.setText(name);

                String meaning = dataSnapshot.child("Meaning").getValue(String.class);
                d_meaning = (TextView) findViewById(R.id.meaning_detail);
                d_meaning.setText(meaning);

                String sentence1 = dataSnapshot.child("Example1").getValue(String.class);
                d_sentence1 = (TextView) findViewById(R.id.sentence1);
                d_sentence1.setText(sentence1);

                String s_meaning1 = dataSnapshot.child("Example1_Meaning").getValue(String.class);
                d_meaning1 = (TextView) findViewById(R.id.sentence1_meaning);
                d_meaning1.setText(s_meaning1);

                String sentence2 = dataSnapshot.child("Example2").getValue(String.class);
                d_sentence2 = (TextView) findViewById(R.id.sentence2);
                d_sentence2.setText(sentence2);

                String s_meaning2 = dataSnapshot.child("Example2_Meaning").getValue(String.class);
                d_meaning2 = (TextView) findViewById(R.id.sentence2_meaning);
                d_meaning2.setText(s_meaning2);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
    }


    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId=this.hashCode() + "";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

}
