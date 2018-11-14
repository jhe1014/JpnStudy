package com.project.jpnstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

public class TestLevelSelect extends AppCompatActivity { // 문제은행 선택 메뉴
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
