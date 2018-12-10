package com.project.jpnstudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class FavoritesAdapter extends ArrayAdapter<FavoritesListData> {

    private ArrayList<FavoritesListData> f_listCustom;

    public FavoritesAdapter(Context context, int textViewResourceId, ArrayList<FavoritesListData> items) {
        super(context, textViewResourceId, items);
        this.f_listCustom = items;
    }

    public int getCount() {
        return f_listCustom.size();
    }

    public FavoritesListData getItem(int position) {
        return f_listCustom.get(position);
    }

    public int getPosition(FavoritesListData item) {
        return f_listCustom.indexOf(item);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_favorites, null);
        }

        FavoritesListData p = f_listCustom.get(position);

        if (p != null) {
            final TextView tw = (TextView) v.findViewById(R.id.f_word);
            final TextView tm = (TextView) v.findViewById(R.id.f_meaning);

            tw.setText(p.getf_Word());
            tm.setText(p.getf_Meaning());

            final ImageView ib_star = (ImageView) v.findViewById(R.id.f_star);
            final ImageView ib_hs = (ImageView) v.findViewById(R.id.f_headset);
        }
        return v;
    }

}