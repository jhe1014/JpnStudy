package com.project.jpnstudy;

import android.graphics.drawable.Drawable;

public class ListData {
    private String tWord;
    private String tMeaning;
    private int starIcon;
    private int headsetIcon;

    public String gettWord() {
        return tWord;
    }

    public void settWord(String word) {
        tWord = word;
    }

    public String gettMeaning() {
        return tMeaning;
    }

    public void settMeaning(String meaning) {
        tMeaning = meaning;
    }

    public int getStarIcon() {
        return starIcon;
    }

    public void setStarIcon(int sIcon) {
        starIcon = sIcon;
    }

    public int getHeadsetIcon() {
        return headsetIcon;
    }

    public void setHeadsetIcon(int hsIcon) {
        headsetIcon = hsIcon;
    }
}
