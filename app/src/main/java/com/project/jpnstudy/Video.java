package com.project.jpnstudy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Video extends YouTubeBaseActivity {
    Toolbar toolbar;
    YouTubePlayerView youTubeView;
    Button button;

    YouTubePlayer.OnInitializedListener listener;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    Integer row_num;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        setTitle("");

        Intent intent = getIntent();
        row_num = intent.getExtras().getInt("number");

        button = (Button)findViewById(R.id.youtube_button);
        youTubeView = (YouTubePlayerView)findViewById(R.id.youtube_view);

        setData(row_num);

        listener = new YouTubePlayer.OnInitializedListener(){
            //초기화 성공시
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(key);//url의 맨 뒷부분 ID값만 넣으면 됨
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

        };

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //첫번째 인자는 API키값 두번째는 실행할 리스너객체를 넘겨줌
                youTubeView.initialize("AIzaSyD33qSgCu1V2sYwZe3tuT29ckxPDsqH2oU", listener);
            }
        });
    }

    private void setData(Integer qn) {
        databaseReference.child("Url").child(qn.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                key = dataSnapshot.child("url").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
