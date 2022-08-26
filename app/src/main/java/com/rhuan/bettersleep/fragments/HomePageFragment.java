package com.rhuan.bettersleep.fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.rhuan.bettersleep.R;
import com.rhuan.bettersleep.adapter.MusicAdapter;
import com.rhuan.bettersleep.entities.Music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class HomePageFragment extends Fragment {

    private List<Music> musicList;
    private RecyclerView rv;

    public HomePageFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        rv = view.findViewById(R.id.musics);

        musicList = Arrays.asList(
                Music.MusicBuilder.builder()
                        .setMusicName("Judul1")
                        .setMusicImage(getContext().getDrawable(R.drawable.music1))
                        .build(),
                Music.MusicBuilder.builder()
                        .setMusicName("Judul2")
                        .setMusicImage(getContext().getDrawable(R.drawable.music2))
                        .build(),
                Music.MusicBuilder.builder()
                        .setMusicName("Judul3")
                        .setMusicImage(getContext().getDrawable(R.drawable.music3))
                        .build()
        );
        LinearLayoutManager llm = new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(llm);
        rv.setAdapter(new MusicAdapter(musicList));

        return view;
    }
}