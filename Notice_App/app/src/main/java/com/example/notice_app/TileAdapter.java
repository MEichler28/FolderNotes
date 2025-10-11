package com.example.notice_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_FOLDER = 1;
    private static final int VIEW_NOTE   = 2;
    // (Optional für Audio: VIEW_AUDIO = 3)

    private final List<Tile> items = new ArrayList<>();

    @Override public int getItemViewType(int position) {
        Tile t = items.get(position);
        if (t.type == Tile.Type.FOLDER) return VIEW_FOLDER;
        else return VIEW_NOTE; // vereinfachen; Audio kannst du analog ergänzen
    }

    @NonNull @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_FOLDER) {
            View v = inf.inflate(R.layout.item_folder, parent, false);
            return new FolderVH(v);
        } else {
            View v = inf.inflate(R.layout.item_note, parent, false);
            return new NoteVH(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int pos) {
        Tile t = items.get(pos);
        if (h instanceof FolderVH) ((FolderVH) h).bind(t);
        else ((NoteVH) h).bind(t);
    }

    @Override public int getItemCount() { return items.size(); }

    public void submit(List<Tile> newItems) {
        items.clear();
        if (newItems != null) items.addAll(newItems);
        notifyDataSetChanged(); // fürs Erste ok; später DiffUtil
    }

    static class FolderVH extends RecyclerView.ViewHolder {
        final ImageView icon; final TextView title;
        FolderVH(@NonNull View v) { super(v); icon = v.findViewById(R.id.icon); title = v.findViewById(R.id.title); }
        void bind(Tile t) {
            title.setText(t.title);
            if (t.iconRes != 0) icon.setImageResource(t.iconRes);
        }
    }

    static class NoteVH extends RecyclerView.ViewHolder {
        final ImageView icon; final TextView title; final TextView snippet;
        NoteVH(@NonNull View v) { super(v); icon = v.findViewById(R.id.icon); title = v.findViewById(R.id.title); snippet = v.findViewById(R.id.snippet); }
        void bind(Tile t) {
            title.setText(t.title);
            snippet.setText(t.snippet != null ? t.snippet : "");
            if (t.iconRes != 0) icon.setImageResource(t.iconRes);
        }
    }
}
