package com.project.jpnstudy;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class ListViewAdapter extends ArrayAdapter<ListData> implements TextToSpeech.OnInitListener{
    private ArrayList<ListData> listCustom;

    private TextToSpeech tts;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    Integer num = 1;

    public ListViewAdapter(Context context, int textViewResourceId, ArrayList<ListData> items) {
        super(context, textViewResourceId, items);
        this.listCustom = items;
    }

    public int getCount(){
        return listCustom.size();
    }

    public ListData getItem(int position) {
        return listCustom.get(position);
    }

    public int getPosition(ListData item) {
        return listCustom.indexOf(item);
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.wordlist_item, null);
        }

        ListData p = listCustom.get(position);

        if (p != null) {
            final TextView tw = (TextView) v.findViewById(R.id.word);
            final TextView tm = (TextView) v.findViewById(R.id.meaning);

            tw.setText(p.gettWord());
            tm.setText(p.gettMeaning());

            final ImageView ib_star = (ImageView) v.findViewById(R.id.btn_star);
            final ImageView ib_hs = (ImageView) v.findViewById(R.id.btn_headset);

            ib_hs.setTag(position);
            ib_star.setTag(position);

            ib_hs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = Integer.parseInt((v.getTag().toString()));
                    ListData p = listCustom.get(position);
                    String text = tw.getText().toString();
                    //Log.v("단어", text);

                    if(p != null) {
                        //tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
                    }
                }
            });

            ib_star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        int position = Integer.parseInt((v.getTag().toString()));
                        ListData p = listCustom.get(position);
                        String name = tw.getText().toString();
                        //Log.v("단어", text);
                        String meaning = tm.getText().toString();

                        writeFavoriteData(name, meaning);
                        Toast.makeText(getContext(), "즐겨찾기에 등록되었습니다!", Toast.LENGTH_LONG).show();
                        ib_star.setImageResource(R.drawable.baseline_star_black_24);
                        num++;
                    }
            });
        }

        return v;
    }

    private void writeFavoriteData(String word, String meaning) {
        WordData data = new WordData(word, meaning);
        databaseReference.child("Like").child(num.toString()).setValue(data);
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            tts.setLanguage(Locale.JAPANESE);
        }
    }
}

class WordListData {
    public String word;
    public String meaning;

    public WordListData() {

    }

    public WordListData(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;

    }
}
