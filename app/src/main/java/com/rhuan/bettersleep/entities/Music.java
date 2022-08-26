package com.rhuan.bettersleep.entities;

import android.graphics.drawable.Drawable;

public class Music {

    private final String music_name;
    private final Drawable music_image;

    public Music(MusicBuilder builder) {
        this.music_name = builder.music_name;
        this.music_image = builder.music_image;
    }

    public String getMusicName() {
        return music_name;
    }

    public Drawable getMusicImage() {
        return music_image;
    }

    public static class MusicBuilder {
        private String music_name;
        private Drawable music_image;

        public MusicBuilder setMusicName(String music_name) {
            this.music_name = music_name;
            return this;
        }

        public MusicBuilder setMusicImage(Drawable music_image) {
            this.music_image = music_image;
            return this;
        }

        public static MusicBuilder builder(){
            return new MusicBuilder();
        }

        public Music build(){
            return new Music(this);
        }

    }
}
