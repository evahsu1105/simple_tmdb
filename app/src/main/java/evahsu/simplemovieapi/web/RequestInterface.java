package evahsu.simplemovieapi.web;


import java.util.HashMap;
import java.util.Map;

import evahsu.simplemovieapi.util.Constants;
import evahsu.simplemovieapi.web.movie.MovieDetailResponse;
import evahsu.simplemovieapi.web.movie.MovieDiscoverResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestInterface {
    //1. discover movie https://api.themoviedb.org/3/discover/movie?api_key=ab52bf6ff8ba7e3511c4b8ace26441f7&language=zh&sort_by=release_date.desc&include_adult=false&include_video=false&page=2&primary_release_date.gte
    @GET(Constants.PATH_MOVIE_DISCOVER)
    Observable<MovieDiscoverResponse> discoverMovie(@Query("api_key") String apiKey, @Query("sort_by") String sortBy, @Query("include_video") boolean includeVideo
            , @Query("primary_release_date.gte") String dateGreaterThanEqual,@Query("primary_release_date.lte") String dateLessThanEqual, @Query("language") String language,@Query("page") int page);

    //1-1. discover movie https://api.themoviedb.org/3/discover/movie?api_key=ab52bf6ff8ba7e3511c4b8ace26441f7&language=zh&sort_by=release_date.desc&include_adult=false&include_video=false&page=2&primary_release_date.gte
    @GET(Constants.PATH_MOVIE_DISCOVER)
    Observable<MovieDiscoverResponse> discoverMovie(@Query("api_key") String apiKey, @Query("sort_by") String sortBy, @Query("include_video") boolean includeVideo
            ,@Query("primary_release_date.lte") String dateLessThanEqual,@Query("page") int page);

    //2. now playing https://api.themoviedb.org/3/movie/now_playing?api_key=ab52bf6ff8ba7e3511c4b8ace26441f7&language=zh&page=1&region=
    @GET(Constants.PATH_MOVIE_NOW_PLAYING)
    Observable<MovieDiscoverResponse> nowPlayingMovie(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);
    //3.
    @GET(Constants.PATH_MOVIE_DETAIL)
    Observable<MovieDetailResponse> movieDetail(@Path("id") int user, @Query("api_key") String apiKey, @Query("language") String language, @Query("append_to_response") String appendToResponse);

}
