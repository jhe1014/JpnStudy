package com.project.jpnstudy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class FavoritesAdapter extends ArrayAdapter<ListData>{
        private ArrayList<ListData> listCustom;


    public FavoritesAdapter(@NonNull Context context, int resource, ArrayList<ListData> textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    @Override
    public int getCount() {
        return listCustom.size();
    }

    public int getPosition(ListData item) {
        return listCustom.indexOf(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_favorites, null);
        }

        ListData p = listCustom.get(position);

        if (p != null) {
            TextView tw = (TextView) v.findViewById(R.id.f_word);
            TextView tm = (TextView) v.findViewById(R.id.f_meaning);

            tw.setText(p.gettWord());
            tm.setText(p.gettMeaning());

            ImageView ib_star = (ImageView) v.findViewById(R.id.btn_star);
            ImageView ib_hs = (ImageView) v.findViewById(R.id.btn_headset);
        }

        return v;
    }

}
