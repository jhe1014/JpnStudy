package com.project.jpnstudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class FavoritesAdapter extends BaseAdapter {
    private ArrayList<ListData> listCustom = new ArrayList<>();

    @Override
    public int getCount() {
        return listCustom.size();
    }

    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordlist_item, null, false);

            holder = new CustomViewHolder();
            holder.textWord = (TextView) convertView.findViewById(R.id.word);
            holder.textMeaning = (TextView) convertView.findViewById(R.id.meaning);
            holder.imgStar = (ImageView) convertView.findViewById(R.id.Image_star);
            holder.imgHead = (ImageView) convertView.findViewById(R.id.Image_headset);

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        ListData listdata = listCustom.get(position);

        holder.textWord.setText(listdata.gettWord());
        holder.textMeaning.setText(listdata.gettMeaning());
        holder.imgStar.setImageResource(listdata.getStarIcon());
        holder.imgHead.setImageResource(listdata.getHeadsetIcon());

        return convertView;
    }

    class CustomViewHolder {
        TextView textWord;
        TextView textMeaning;
        ImageView imgStar;
        ImageView imgHead;
    }

    public void addItem(ListData listData) {
        listCustom.add(listData);
    }

}
