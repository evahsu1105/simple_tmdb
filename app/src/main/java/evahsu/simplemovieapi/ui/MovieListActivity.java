package evahsu.simplemovieapi.ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import evahsu.simplemovieapi.R;
import evahsu.simplemovieapi.debug.LogHelper;
import evahsu.simplemovieapi.intf.ObjectKeyRecyclerViewListener;
import evahsu.simplemovieapi.util.Constants;
import evahsu.simplemovieapi.util.Util;
import evahsu.simplemovieapi.util.WebUtil;
import evahsu.simplemovieapi.web.ObserverOnNextListener;
import evahsu.simplemovieapi.web.ProgressObserver;
import evahsu.simplemovieapi.web.RequestInterface;
import evahsu.simplemovieapi.web.movie.MovieDiscoverResponse;
import evahsu.simplemovieapi.web.movie.Result;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MovieListActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private InnerObjectKeyRecyclerViewListener innerObjectKeyRecyclerViewListener = new InnerObjectKeyRecyclerViewListener();
    private GetMovieListObserverListener getPushListObserverListener = new GetMovieListObserverListener();
    private InnerOnScrollListener innerOnScrollListener = new InnerOnScrollListener();
    @Nullable
    @BindView(R.id.movie_list_recyclerView)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(MovieListActivity.this);
        initRecyclerView();
    }

    private void initRecyclerView(){
        movieAdapter = new MovieAdapter(getApplicationContext());
        movieAdapter.setRecyclerViewListener(innerObjectKeyRecyclerViewListener);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.addOnScrollListener(innerOnScrollListener);
        getMovieList();
    }

    public void getMovieList(){
        //檢查網路
        if(!Util.isInternetOn(getBaseContext())){
            Util.showShortToast(getBaseContext(),R.string.internet_loss);
            return;
        }
        Retrofit retrofit = WebUtil.getRetrofitRxJava(Constants.DEFAULT_TIME_OUT,Constants.URL_MOVIE_BASE, true,"getMovieList");
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        requestInterface.nowPlayingMovie(Constants.MOVIE_API_KEY,getResources().getConfiguration().locale.toLanguageTag(),movieAdapter.getNextLastRequestPages())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<MovieDiscoverResponse>(MovieListActivity.this, getPushListObserverListener));


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    class InnerObjectKeyRecyclerViewListener implements ObjectKeyRecyclerViewListener {

        @Override
        public void onItemClick(int key, Object bean) {
            if(bean == null){
                LogHelper.logToCloud("",String.format("ReadModeItemClick null:%s",key));
                return;
            }
            Result result = (Result) bean;
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.EXTRA_MOVID_ID,result.getId());
            Util.startActivity(MovieListActivity.this,MovieDetailActivity.class,bundle);
        }

        @Override
        public boolean onItemLongClick(int key, Object bean) {
//            DialogHandler.showAlertDialog(getActivity(),simpleListItem.getContent(),null);
//            LuxgenUtils.showShortToast(getContext(),String.format("%s long clicked",simpleListItem.getTitle()));
            return true;
        }
    }
    class GetMovieListObserverListener implements ObserverOnNextListener<MovieDiscoverResponse> {
        @Override
        public void onNext(MovieDiscoverResponse resp) {
            try{
                //server回應為空
                if(resp == null){
                    Util.showShortToast(getBaseContext(),R.string.system_is_busy);
                    return;
                }
                movieAdapter.addData(resp.getResults());
                movieAdapter.setTotalPages(resp.getTotal_pages());
                movieAdapter.setLastFinishRequestPages(movieAdapter.getLastRequestPages());
                movieAdapter.notifyDataSetChanged();
            }catch (Exception e){
                Util.showShortToast(getBaseContext(),R.string.system_is_busy);
                LogHelper.logToCloud(String.format("getMovieList_fail |%s", e.getMessage()));
                LogHelper.reportCrash(e);
            }
        }

        @Override
        public void onError() {
            movieAdapter.setLastRequestPages(movieAdapter.getLastFinishRequestPages());
        }

    }
    class InnerOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
//            -1 for up, 1 for down, 0 will always return false.
            if (!recyclerView.canScrollVertically(1)) {
                if(!movieAdapter.isReadyToLoad()){
                    return;
                }
                if(movieAdapter.isMorePageToLoad()){
                    getMovieList();
                }else{
                    Util.showShortToast(getBaseContext(),R.string.no_more_to_load);
                }

            }
        }
    }
}
