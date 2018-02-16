package evahsu.simplemovieapi.web.movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evahsu on 2018/2/14.
 */

public class MovieDiscoverResponse {
    public Integer page;
    public Integer total_results;
    public Integer total_pages;
    public ArrayList<Result> results = null;
}
