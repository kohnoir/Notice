package com.example.notice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
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
    private OnItemClickListener<Note> onItemClickListener;
    NoteConstructorActivity noteConstructorActivity;
    String allTextNote;

    NotesAdapter(Context context, List<Note> notes) {
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, final int position) {
        final Note note = notes.get(position);
        holder.headView.setText(note.getTextHeading());
        holder.bodyView.setText(note.getTextBody());
        holder.timeView.setText(note.getCurrentTime());
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

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(itemView);

        // сетим слушателя нажатий на нужный компонент
        holder.itemView.setOnClickListener(v -> {
            // получаем позицию в адаптере, которой соответсвует холдер
            int position = holder.getAdapterPosition();
            // если холдер соответсвует какой-либо позиции в адаптере
            if (position != RecyclerView.NO_POSITION) {
                // уведомляем слушателя о нажатии
                fireItemClicked(position, notes.get(position));
                Intent intentNote = new Intent(itemView.getContext(),NoteConstructorActivity.class);
                Note note = notes.get(position);
                allTextNote = note.getTextHeading() + note.getTextBody();
                intentNote.putExtra("textNoteAdapter",allTextNote);
                itemView.getContext().startActivity(intentNote);
                Intent intentMain = new Intent(itemView.getContext(),MainActivity.class);
                intentMain.putExtra("position",position);

            }
        });
        return holder;
    }

    private void fireItemClicked(int position, Note item) {

        if (onItemClickListener != null) {
            onItemClickListener.onItemClicked(position, item);
        }
    }
    // суда подписываемся активитей, фрагментом, перезнтером или чем нибудь еще
    public void setOnItemClickListener(OnItemClickListener<Note> listener) {
        onItemClickListener = listener;
    }

    // реализуем подписчиком
    public interface OnItemClickListener<T> {
        void onItemClicked(int position, T item);
    }
    public void removeAt(int position) {
        notes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, notes.size());
    }

}