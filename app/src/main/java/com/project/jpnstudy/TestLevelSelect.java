package com.project.jpnstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TestLevelSelect extends AppCompatActivity implements View.OnClickListener { // 문제은행 선택 메뉴
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_level_select);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("문제은행");

        Button btn1 = (Button) findViewById(R.id.jlpt_n1);
        Button btn2 = (Button) findViewById(R.id.jlpt_n2);
        Button btn3 = (Button) findViewById(R.id.jlpt_n3);
        Button btn4 = (Button) findViewById(R.id.jlpt_n4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int select_level = -1;
        Intent intent = new Intent(getApplicationContext(), TestActivity.class);
        switch (v.getId()) {
            case R.id.jlpt_n1 : select_level = 1; break;
            case R.id.jlpt_n2 : select_level = 2; break;
            case R.id.jlpt_n3 : select_level = 3; break;
            case R.id.jlpt_n4 : select_level = 4; break;
        }
        intent.putExtra("select_level", select_level);
        startActivity(intent);
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
