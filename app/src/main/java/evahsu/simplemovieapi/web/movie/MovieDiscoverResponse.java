package evahsu.simplemovieapi.web.movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evahsu on 2018/2/14.
 */

public class MovieDiscoverResponse {
    private Integer page;
    private Integer total_results;
    private Integer total_pages;
    private ArrayList<Result> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
