package com.sergiocruz.mostpopularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 19/02/2018.
 * Creating Parcelable with
 * http://www.parcelabler.com/
 */

class MovieObject implements Parcelable {

    private Integer vote_count;
    private Integer id;
    private Boolean video;
    private Float vote_average;
    private String title;
    private Float popularity;
    private String poster_path;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids;
    private String backdrop_path;
    private Boolean adult;
    private String overview;
    private String release_date;
    private Boolean isFavorite;
    private String posterFilePath;
    private String backdropFilePath;

    MovieObject(Integer vote_count, Integer id, Boolean video, Float vote_average, String title, Float popularity, String poster_path, String original_language, String original_title, List<Integer> genre_ids, String backdrop_path, Boolean adult, String overview, String release_date, Boolean isFavorite, String posterFilePath, String backdropFilePath) {
        this.vote_count = vote_count;
        this.id = id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.isFavorite = isFavorite;
        this.posterFilePath = posterFilePath;
        this.backdropFilePath = backdropFilePath;
    }

    MovieObject(Parcel in) {
        vote_count = in.readByte() == 0x00 ? null : in.readInt();
        id = in.readByte() == 0x00 ? null : in.readInt();
        byte videoVal = in.readByte();
        video = videoVal == 0x02 ? null : videoVal != 0x00;
        vote_average = in.readByte() == 0x00 ? null : in.readFloat();
        title = in.readString();
        popularity = in.readByte() == 0x00 ? null : in.readFloat();
        poster_path = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        if (in.readByte() == 0x01) {
            genre_ids = new ArrayList<>();
            in.readList(genre_ids, Integer.class.getClassLoader());
        } else {
            genre_ids = null;
        }
        backdrop_path = in.readString();
        byte adultVal = in.readByte();
        adult = adultVal == 0x02 ? null : adultVal != 0x00;
        overview = in.readString();
        release_date = in.readString();
        byte isFavVal = in.readByte();
        isFavorite = isFavVal == 0x02 ? null : isFavVal != 0x00;
        posterFilePath = in.readString();
        backdropFilePath = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (vote_count == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(vote_count);
        }
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        if (video == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (video ? 0x01 : 0x00));
        }
        if (vote_average == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeFloat(vote_average);
        }
        dest.writeString(title);
        if (popularity == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeFloat(popularity);
        }
        dest.writeString(poster_path);
        dest.writeString(original_language);
        dest.writeString(original_title);
        if (genre_ids == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(genre_ids);
        }
        dest.writeString(backdrop_path);
        if (adult == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (adult ? 0x01 : 0x00));
        }
        dest.writeString(overview);
        dest.writeString(release_date);

        if (isFavorite == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (isFavorite ? 0x01 : 0x00));
        }
        dest.writeString(posterFilePath);
        dest.writeString(backdropFilePath);

    }

    Integer getVote_count() {
        return vote_count;
    }

    Integer getId() {
        return id;
    }

    Boolean getVideo() {
        return video;
    }

    Float getVote_average() {
        return vote_average;
    }

    String getTitle() {
        return title;
    }

    Float getPopularity() {
        return popularity;
    }

    String getOriginal_language() {
        return original_language;
    }

    String getOriginal_title() {
        return original_title;
    }

    List<Integer> getGenre_ids() {
        return genre_ids;
    }

    String getBackdrop_path() {
        return backdrop_path;
    }

    Boolean getAdult() {
        return adult;
    }

    String getOverview() {
        return overview;
    }

    String getRelease_date() {
        return release_date;
    }

    String getPoster_path() {
        return poster_path;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public String getPosterFilePath() {
        return posterFilePath;
    }

    public String getBackdropFilePath() {
        return backdropFilePath;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public void setPosterFilePath(String posterFilePath) {
        this.posterFilePath = posterFilePath;
    }

    public void setBackdropFilePath(String backdropFilePath) {
        this.backdropFilePath = backdropFilePath;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieObject> CREATOR = new Parcelable.Creator<MovieObject>() {
        @Override
        public MovieObject createFromParcel(Parcel in) {
            return new MovieObject(in);
        }

        @Override
        public MovieObject[] newArray(int size) {
            return new MovieObject[size];
        }
    };

}