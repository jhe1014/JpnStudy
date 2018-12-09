package com.project.jpnstudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

class YoutubeListAdapter extends ArrayAdapter<YoutubeListData> {
    private ArrayList<YoutubeListData> listCustom;

    public YoutubeListAdapter(Context context, int textViewResourceId, ArrayList<YoutubeListData> items) {
        super(context, textViewResourceId, items);
        this.listCustom = items;
    }

    public int getCount() {
        return listCustom.size();
    }

    public YoutubeListData getItem(int position) {
        return listCustom.get(position);
    }

    public int getPosition(YoutubeListData item) {
        return listCustom.indexOf(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.youtube_list_data, null);
        }

        YoutubeListData p = listCustom.get(position);

        if (p != null) {
            final TextView tw = (TextView) v.findViewById(R.id.y_title);

            tw.setText(p.gety_title());
        }

        return v;
    }
}

