package com.project.jpnstudy;

import android.graphics.drawable.Drawable;

public class ListData {
    private String tWord;
    private String tMeaning;
   // private int starIcon;
   // private int headsetIcon;

    public ListData(String _word, String _meaning) {
        this.tWord = _word;
        this.tMeaning = _meaning;
    }

    public String gettWord() {
        return tWord;
    }

    /*public void settWord(String word) {
        tWord = word;
    }*/

    public String gettMeaning() {
        return tMeaning;
    }

}
