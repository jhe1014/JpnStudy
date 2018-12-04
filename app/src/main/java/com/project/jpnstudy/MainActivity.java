package com.project.jpnstudy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView_left;
    NavigationView navigationView_right;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    TextView word;
    TextView wmeaning;
    TextView sentence;
    TextView smeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView_left = (NavigationView) findViewById(R.id.home_navigation_view);
        navigationView_right = (NavigationView) findViewById(R.id.home_navigation_view_right);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_dehaze_black_24);
        setTitle("");



        navigationView_left.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setCheckable(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                Intent intent;
                switch (id) {
                    case R.id.menu01 : // 단어목록
                        intent = new Intent(getApplicationContext(), WordListActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu02 : // 문제은행
                        intent = new Intent(getApplicationContext(), TestLevelSelect.class);
                        startActivity(intent);
                        break;

                    case R.id.menu03 : // 단어퀴즈
                        intent = new Intent(getApplicationContext(), WordTestActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu04 : // 즐겨찾기
                        intent = new Intent(getApplicationContext(), Favorites.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        navigationView_right.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setCheckable(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                Intent intent;
                switch (id) {
                    case R.id.menu04 : // 통계
                        intent = new Intent(getApplicationContext(), GraphActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu05 : // 설정
                        intent = new Intent(getApplicationContext(), SettingActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu06 : // 유튜브
                        intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        setData();
    }

    private void setData() {
       databaseReference.child("Word").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Name").getValue(String.class);
                word = (TextView) findViewById(R.id.today_word);
                Log.v("텍스트", name);
                word.setText(name);

                String meaning = dataSnapshot.child("Meaning").getValue(String.class);
                wmeaning = (TextView) findViewById(R.id.today_word_meaning);
                Log.v("텍스트", meaning);
                wmeaning.setText(meaning);

                String s_name = dataSnapshot.child("Example1").getValue(String.class);
                sentence = (TextView) findViewById(R.id.today_sentence);
                Log.v("텍스트", s_name);
                sentence.setText(s_name);

                String s_meaning = dataSnapshot.child("Example1_Meaning").getValue(String.class);
                smeaning = (TextView) findViewById(R.id.today_sentence_meaning);
                Log.v("텍스트", s_meaning);
                smeaning.setText(s_meaning);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    }

