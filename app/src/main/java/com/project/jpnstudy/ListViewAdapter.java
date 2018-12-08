package com.project.jpnstudy;

import android.content.Context;
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

import static java.security.AccessController.getContext;

public class ListViewAdapter extends ArrayAdapter<ListData> {
    private ArrayList<ListData> listCustom;

    public ListViewAdapter(Context context, int textViewResourceId, ArrayList<ListData> items) {
        super(context, textViewResourceId, items);
        this.listCustom = items;
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

            ImageButton ib_star = (ImageButton) v.findViewById(R.id.btn_star);
            ImageButton ib_hs = (ImageButton) v.findViewById(R.id.btn_headset);
        }

        return v;
    }

}
