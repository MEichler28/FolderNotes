package com.example.notice_app.model;

public class Tile {
    public enum Type { FOLDER, NOTE, AUDIO, IMAGE, DRAWING, CHECKLIST }


    public final Type type;
    public final String title;
    public final String snippet; // nur für NOTE
    public final int iconRes;    // optional, pro Typ

    public Tile(Type type, String title, String snippet, int iconRes) {
        this.type = type;
        this.title = title;
        this.snippet = snippet;
        this.iconRes = iconRes;
    }
}
