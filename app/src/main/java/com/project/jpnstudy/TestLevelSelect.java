package com.project.jpnstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class TestLevelSelect extends AppCompatActivity { // 문제은행 선택 메뉴

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_level_select);

        Button btn1 = (Button)findViewById(R.id.jlpt_n1);
        Button btn2 = (Button)findViewById(R.id.jlpt_n2);
        Button btn3 = (Button)findViewById(R.id.jlpt_n3);
        Button btn4 = (Button)findViewById(R.id.jlpt_n4);

    }
}
