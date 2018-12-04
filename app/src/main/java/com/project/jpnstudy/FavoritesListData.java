package com.project.jpnstudy;

public class FavoritesListData {
    private String f_Word;
    private String f_Meaning;
    private int f_starIcon;
    private int f_headsetIcon;

    public String getf_Word() {
        return f_Word;
    }

    public void setf_Word(String word) {
        f_Word = word;
    }

    public String getf_Meaning() {
        return f_Meaning;
    }

    public void setf_Meaning(String meaning) {
        f_Meaning = meaning;
    }

    public int getf_StarIcon() {
        return f_starIcon;
    }

    public void setf_StarIcon(int sIcon) {
        f_starIcon = sIcon;
    }

    public int getf_HeadsetIcon() {
        return f_headsetIcon;
    }

    public void setf_HeadsetIcon(int hsIcon) {
        f_headsetIcon = hsIcon;
    }
}
