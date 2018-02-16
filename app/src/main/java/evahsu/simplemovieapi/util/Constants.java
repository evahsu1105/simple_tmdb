package evahsu.simplemovieapi.util;

import android.graphics.Color;

/**
 * Created by EvaHsu on 2017/12/21.
 */

public class Constants {
    public final static String URL_MOVIE_BASE = "https://api.themoviedb.org/3/";
    public final static String URL_POSTER_BASE = "https://image.tmdb.org/t/p/w500";
    public final static String PATH_MOVIE_DISCOVER = "discover/movie";
    public final static String PATH_MOVIE_DETAIL = "movie/{id}";
    public final static String PATH_MOVIE_NOW_PLAYING = "movie/now_playing";
    public final static String EXTRA_MOVID_ID = "extra_movie_id";
    public final static String APPEND_TO_REQUEST_CREDITS = "credits";


    public final static String MOVIE_API_KEY = "caf752bfkeef4418ba7e3511c4bce222";
    public final static String VALUE_SORT_RELEASE_DATE_DESC = "release_date.desc";
    public static final long LONG_TIME_OUT = 1800;
    public static final long DEFAULT_TIME_OUT = 180;
}
