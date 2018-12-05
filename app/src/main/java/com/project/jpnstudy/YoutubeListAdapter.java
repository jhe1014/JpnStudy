package com.project.jpnstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

class YoutubeListAdaptr extends BaseAdapter {

            private ArrayList<YoutubeitesListData> listCustom = new ArrayList<>();

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
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_youtube_list_data, null, false);
//////////////
                    holder = new CustomViewHolder();
                    holder.textWord = (TextView) convertView.findViewById(R.id.f_word);
                    holder.textMeaning = (TextView) convertView.findViewById(R.id.f_meaning);
///////////////
                    convertView.setTag(holder);
                } else {
                    holder = (CustomViewHolder) convertView.getTag();
                }

                YoutubeitesListData y_listdata = listCustom.get(position);

                holder.textWord.setText(y_listdata.gety_data());
                holder.textMeaning.setText(y_listdata.gety_title());
                return convertView;
            }

            class CustomViewHolder {
                TextView textWord;
                TextView textMeaning;
            }

            public void addItem(YoutubeitesListData listData) {
                listCustom.add(listData);
            }

        }

