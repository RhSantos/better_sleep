package com.rhuan.bettersleep.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rhuan.bettersleep.R;
import com.rhuan.bettersleep.entities.Music;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder>{

    private List<Music> musicList;

    public MusicAdapter(List<Music> musicList){
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_page_item,parent,false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Music music = musicList.get(position);
        holder.bind(music);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    class MusicViewHolder extends RecyclerView.ViewHolder{

        private TextView rv_text;
        private ImageView rv_image;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_text = itemView.findViewById(R.id.rv_text);
            rv_image = itemView.findViewById(R.id.rv_image);
        }

        public void bind(Music music){
            rv_text.setText(music.getMusicName());
            rv_image.setImageDrawable(music.getMusicImage());
        }
    }
}


