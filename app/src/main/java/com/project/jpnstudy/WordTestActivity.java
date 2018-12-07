package com.project.jpnstudy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class WordTestActivity extends AppCompatActivity {
    Toolbar toolbar;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    TextView Word;
    TextView result;
    RadioButton op1;
    RadioButton op2;
    RadioButton op3;
    RadioButton op4;
    Button ok_btn;
    Button next_btn;

    Integer correct_answer;
    Integer select_answer;
    Integer i = 1;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_test);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("단어퀴즈");

        final View nb = findViewById(R.id.word_next_button);
        nb.setVisibility(View.INVISIBLE);

        setData(i);

        ok_btn = findViewById(R.id.word_test_ok);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = findViewById(R.id.word_test_result);
                if(select_answer == correct_answer) {
                    result.setText("정답입니다!");
                    nb.setVisibility(View.VISIBLE);
                }
                else result.setText("오답입니다ㅠㅠ");
            }
        });

        final RadioGroup wrg = findViewById(R.id.word_test_rg);
        wrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedID = checkedId;

                switch (selectedID) {
                    case R.id.word_option1 : select_answer = 1; break;
                    case R.id.word_option2 : select_answer = 2; break;
                    case R.id.word_option3 : select_answer = 3; break;
                    case R.id.word_option4 : select_answer = 4; break;
                }
                Log.v("옵션값", Integer.toString(select_answer));
            }
        });

        next_btn = findViewById(R.id.word_next_button);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = i + 1;
                nb.setVisibility(View.INVISIBLE);
                wrg.clearCheck();
                result = findViewById(R.id.word_test_result);
                result.setText("");
                setData(i);
            }
        });
    }

    private void setData(Integer qn) {
        databaseReference.child("Word").child(qn.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    i = 0;
                    Intent intent2 = new Intent(getApplicationContext(), TestEndActivity.class);
                    startActivity(intent2);
                    finish();
                } else {
                    String name = dataSnapshot.child("Name").getValue(String.class);
                    Word = (TextView) findViewById(R.id.word_test);
                    Word.setText(name);

                    String answer1 = dataSnapshot.child("Answer1").getValue(String.class);
                    op1 = (RadioButton) findViewById(R.id.word_option1);
                    op1.setText(answer1);

                    String answer2 = dataSnapshot.child("Answer2").getValue(String.class);
                    op2 = (RadioButton) findViewById(R.id.word_option2);
                    op2.setText(answer2);

                    String answer3 = dataSnapshot.child("Answer3").getValue(String.class);
                    op3 = (RadioButton) findViewById(R.id.word_option3);
                    op3.setText(answer3);

                    String answer4 = dataSnapshot.child("Answer4").getValue(String.class);
                    op4 = (RadioButton) findViewById(R.id.word_option4);
                    op4.setText(answer4);

                    correct_answer = dataSnapshot.child("Correct").getValue(Integer.class);
                    //Log.v("결과값", Integer.toString(correct_answer));
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
                i = 1;
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
