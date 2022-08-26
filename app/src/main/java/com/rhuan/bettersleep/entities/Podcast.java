package com.rhuan.bettersleep.entities;

import android.graphics.drawable.Drawable;

public class Podcast {

    private final String podcast_name;
    private final Drawable podcast_image;

    public Podcast(PodcastBuilder builder) {
        this.podcast_name = builder.podcast_name;
        this.podcast_image = builder.podcast_image;
    }

    public String getMusicName() {
        return podcast_name;
    }

    public Drawable getMusicImage() {
        return podcast_image;
    }

    static class PodcastBuilder {
        private String podcast_name;
        private Drawable podcast_image;

        public PodcastBuilder setMusicName(String podcast_name) {
            this.podcast_name = podcast_name;
            return this;
        }

        public PodcastBuilder setMusicImage(Drawable podcast_image) {
            this.podcast_image = podcast_image;
            return this;
        }

        public PodcastBuilder builder(){
            return new PodcastBuilder();
        }

        public Podcast build(){
            return new Podcast(this);
        }

    }
}
