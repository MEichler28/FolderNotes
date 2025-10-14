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

    // --- Click-Callback für die Activity ---
    public interface OnTileClick { void onClick(Tile tile); }
    private OnTileClick clickListener;
    public void setOnTileClick(OnTileClick listener) { this.clickListener = listener; }

    // --- ViewTypes ---
    private static final int VIEW_FOLDER = 1;
    private static final int VIEW_ITEM   = 2;

    // --- Datenquelle ---
    private final List<Tile> items = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {
        return items.get(position).type == Tile.Type.FOLDER ? VIEW_FOLDER : VIEW_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_FOLDER) {
            View v = inf.inflate(R.layout.item_folder, parent, false);
            return new FolderVH(v);
        } else {
            View v = inf.inflate(R.layout.item_note, parent, false); // nutzt title + icon
            return new NoteVH(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Tile t = items.get(position);

        if (holder instanceof FolderVH) {
            ((FolderVH) holder).bind(t);
        } else {
            ((NoteVH) holder).bind(t);
        }

        // Click hier setzen (statisch sicher, Java 11)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (clickListener != null) clickListener.onClick(t);
            }
        });
    }

    @Override
    public int getItemCount() { return items.size(); }

    /** Einfacher Submit */
    public void submit(List<Tile> data) {
        items.clear();
        if (data != null) items.addAll(data);
        notifyDataSetChanged();
    }

    // ---------- ViewHolders ----------

    static class FolderVH extends RecyclerView.ViewHolder {
        final ImageView icon;
        final TextView title;

        FolderVH(@NonNull View v) {
            super(v);
            icon = v.findViewById(R.id.icon);
            title = v.findViewById(R.id.title);
        }

        void bind(Tile t) {
            title.setText(t.title != null ? t.title : "");
            if (icon != null) {
                if (t.iconRes != 0) icon.setImageResource(t.iconRes);
                else icon.setImageDrawable(null); // niemals setImageResource(null)
            }
        }
    }

    static class NoteVH extends RecyclerView.ViewHolder {
        final ImageView icon;
        final TextView title;

        NoteVH(@NonNull View v) {
            super(v);
            icon = v.findViewById(R.id.icon);
            title = v.findViewById(R.id.title);
        }

        void bind(Tile t) {
            title.setText(t.title != null ? t.title : "");
            if (icon != null) {
                if (t.iconRes != 0) icon.setImageResource(t.iconRes);
                else icon.setImageDrawable(null);
            }
        }
    }
}
