package com.project.jpnstudy;

public class FavoritesListData {
    private String f_Word;
    private String f_Meaning;

    public FavoritesListData(String _word, String _meaning) {
        this.f_Word = _word;
        this.f_Meaning = _meaning;
    }

    public String getf_Word() {
        return f_Word;
    }

    public String getf_Meaning() {
        return f_Meaning;
    }

}
