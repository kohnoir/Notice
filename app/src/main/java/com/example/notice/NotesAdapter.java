package com.example.notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Note> notes;

    NotesAdapter(Context context, List<Note> notes) {
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, final int position) {
        final Note note = notes.get(position);
        holder.headView.setText(note.getTextHeading());
        holder.bodyView.setText(note.getTextBody());
        holder.timeView.setText(note.getCurrentTime());
        holder.timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               touchLogic(note);
               notifyItemRemoved(position);
            }
        });
        holder.bodyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchLogic(note);
                notifyItemRemoved(position);
            }
        });
        holder.headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchLogic(note);
                notifyItemRemoved(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView headView, bodyView,timeView;
        ViewHolder(View view){
            super(view);
            headView = view.findViewById(R.id.text_heading);
            bodyView =  view.findViewById(R.id.text_body);
            timeView = view.findViewById(R.id.text_time);

        }
    }
    private void touchLogic(Note note){
        MainActivity mainActivity = new MainActivity();
        NoteConstructorActivity noteConstructorActivity = new NoteConstructorActivity();
        String textNote = note.getTextHeading()+note.getTextBody();
        noteConstructorActivity.editText(textNote);
        mainActivity.pressNote();
    }
}