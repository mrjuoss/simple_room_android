package com.mrjuoss.lifecycleawaredemo.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrjuoss.lifecycleawaredemo.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context mContext;
    private final LayoutInflater layoutInflater;
    private List<Note> mNotes;

    public NoteAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(itemView);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note note = mNotes.get(position);
            holder.setData(note.getNote(), position);
        } else {
            holder.txtNoteItemView.setText("Tidak ada Catatan - No Note");
        }
    }

    @Override
    public int getItemCount() {
        if (mNotes != null) {
            return mNotes.size();
        } else {
            return 0;
        }
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNoteItemView;
        private int mPosition;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNoteItemView = itemView.findViewById(R.id.txt_note);

        }

        public void setData(String note, int position) {
            txtNoteItemView.setText(note);
            mPosition = position;
        }
    }
}
