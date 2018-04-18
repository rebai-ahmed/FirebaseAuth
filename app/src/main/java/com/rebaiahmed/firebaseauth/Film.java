package com.rebaiahmed.firebaseauth;

/**
 * Created by Rebai Ahmed on 25/02/2018.
 */

public class Film {
    private String filmName;
    private String userId;

    public Film() {
    }

    public Film(String filmName, String userId) {
        this.filmName = filmName;
        this.userId = userId;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getUserId() {
        return userId;
    }
}
