package com.project.jpnstudy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class FavoritesAdapter extends BaseAdapter {
    private ArrayList<FavoritesListData> listCustom = new ArrayList<>();

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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favorites, null, false);

            holder = new CustomViewHolder();
            holder.textWord = (TextView) convertView.findViewById(R.id.f_word);
            holder.textMeaning = (TextView) convertView.findViewById(R.id.f_meaning);
            holder.imgStar = (ImageView) convertView.findViewById(R.id.f_star);
            holder.imgHead = (ImageView) convertView.findViewById(R.id.f_headset);

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        FavoritesListData f_listdata = listCustom.get(position);

        holder.textWord.setText(f_listdata.getf_Word());
        holder.textMeaning.setText(f_listdata.getf_Meaning());
        holder.imgStar.setImageResource(f_listdata.getf_StarIcon());
        holder.imgHead.setImageResource(f_listdata.getf_HeadsetIcon());

        return convertView;
    }

    class CustomViewHolder {
        TextView textWord;
        TextView textMeaning;
        ImageView imgStar;
        ImageView imgHead;
    }

    public void addItem(FavoritesListData listData) {
        listCustom.add(listData);
    }

}
