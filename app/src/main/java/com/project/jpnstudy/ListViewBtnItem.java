package com.project.jpnstudy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*검색 https://m.blog.naver.com/PostView.nhn?blogId=khs7515&logNo=20155584501&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F
*/
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;
/*리스트에 들어갈 데이터 class*/



public class ListViewBtnItem extends AppCompatActivity {
        //내부 클래스를 담을 ArrayList
        ArrayList<ItemData> arItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_btn_item);

        arItem = new ArrayList<ItemData>();
        //클래스의 객체를 선언
        ItemData mi;
        //객체를 생성하여 arItem에 담음
        mi = new ItemData(R.drawable.outline_star_border_white_18dp, "즐겨찾기에 추가");
        arItem.add(mi);
        mi = new ItemData(R.drawable.outline_volume_mute_white_18dp, "듣기");
        arItem.add(mi);
        mi = new ItemData(R.id.word_jpn, "일어");
        arItem.add(mi);
        mi = new ItemData(R.id.word_kr, "뜻");
        arItem.add(mi);

        ListAdapter MyAdapter = new ListAdapter(this, R.layout.activity_list_view_btn_item);

        ListView MyList;

        MyList = (ListView) findViewById(R.id.word_lIst);
        MyList.setAdapter(MyAdapter);
    }
}

//리스트 뷰에 출력할 항목
class ItemData {
  ItemData(String astar,String aheadset,Drawable ajpn,Drawable akr){
      star =astar;
      sound =aheadset;
      jpn =ajpn;
      kr =akr;
}

public MylistAdapter(Context context, int alayout,ArrayList<ItemData>aarSrc){


    maincon = context;
    Inflater = (LayoutInflater)context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE);
    arSrc = aarSrc;
    layout = alayout;
}

    public int getCount() {
        return arSrc.size();
    }

    public String getItem(int position) {
        return arSrc.get(position).Name;
    }

    public long getItemId(int position) {
        return position;
    }

    // 항목 하나를 출력하기 위한 뷰
    // 각 항목의 뷰 생성
    // position -> 생성할 항목의 순서값
    // parent -> 생성되는 뷰의 부모
    // convertView -> 이전에 생성된 차일드 뷰
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if (convertView == null) {
            convertView = Inflater.inflate(layout, parent, false);
        }
        ImageView img = (ImageView)convertView.findViewById(R.id.img);
        img.setImageResource(arSrc.get(position).Icon);

        TextView txt = (TextView)convertView.findViewById(R.id.text);
        txt.setText(arSrc.get(position).Name);

        Button btn = (Button)convertView.findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String str = arSrc.get(pos).Name + "를 주문합니다.";
                Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }



    출처: http://javaexpert.tistory.com/39 [올해는 블록체인이다.]



}
}
