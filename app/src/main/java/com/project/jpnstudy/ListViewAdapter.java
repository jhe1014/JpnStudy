package com.project.jpnstudy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ListViewAdapter extends ArrayAdapter<ListData> {
    private ArrayList<ListData> listCustom;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.wordlist_item, null);
        }

        ListData p = listCustom.get(position);

        if (p != null) {
            TextView tw = (TextView) v.findViewById(R.id.word);
            TextView tm = (TextView) v.findViewById(R.id.meaning);

            tw.setText(p.gettWord());
            tm.setText(p.gettMeaning());

            ImageView ib_star = (ImageView) v.findViewById(R.id.btn_star);
            ImageView ib_hs = (ImageView) v.findViewById(R.id.btn_headset);
        }

        return v;
    }
}
