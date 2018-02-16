package evahsu.simplemovieapi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import evahsu.simplemovieapi.R;
import evahsu.simplemovieapi.debug.LogHelper;
import evahsu.simplemovieapi.intf.ObjectKeyRecyclerViewListener;
import evahsu.simplemovieapi.uicomponent.flowtag.FlowTagLayout;
import evahsu.simplemovieapi.uicomponent.flowtag.OnTagSelectListener;
import evahsu.simplemovieapi.util.Constants;
import evahsu.simplemovieapi.util.Util;
import evahsu.simplemovieapi.util.WebUtil;
import evahsu.simplemovieapi.web.ObserverOnNextListener;
import evahsu.simplemovieapi.web.ProgressObserver;
import evahsu.simplemovieapi.web.RequestInterface;
import evahsu.simplemovieapi.web.movie.Cast;
import evahsu.simplemovieapi.web.movie.MovieDetailResponse;
import evahsu.simplemovieapi.web.movie.MovieDiscoverResponse;
import evahsu.simplemovieapi.web.movie.Result;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.movie_detail_img) ImageView ivPoster;
    @BindView(R.id.movie_detail_title)
    TextView tvTitle;
    @BindView(R.id.movie_detail_genres) FlowTagLayout flowTagGenres;
    @BindView(R.id.movie_detail_date) TextView tvReleaseDate;
    @BindView(R.id.movie_detail_runtime) TextView tvRunTime;
    @BindView(R.id.movie_detail_vote_average) TextView tvRate;
    @BindView(R.id.movie_detail_overview) TextView tvOverView;
    @BindView(R.id.movie_detail_cast_label) TextView tvCastLabel;
    @BindView(R.id.state_block1_oil_green_line) ImageView ivRateLine;
    @BindView(R.id.movie_detail_cast_list) RecyclerView rcCastList;
    private TagAdapter tagAdapter;
    private CastAdapter castAdapter;
    private GetMovieDetailObserverListener getMovieDetailObserverListener = new GetMovieDetailObserverListener();
    private InnerObjectKeyRecyclerViewListener innerObjectKeyRecyclerViewListener = new InnerObjectKeyRecyclerViewListener();
//    @BindView(R.id.movie_detail_runtime) ImageView ivPoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        initFlowTagLayout();//genres
        initRecyclerView();//cast
        if(getIntent() != null && getIntent().getExtras() != null){
            getMovieDetail(getIntent().getExtras().getInt(Constants.EXTRA_MOVID_ID));
        }
    }
    public void initFlowTagLayout(){
        tagAdapter = new TagAdapter(this);

        flowTagGenres.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
        flowTagGenres.setAdapter(tagAdapter);
//        flowTagGenres.setOnTagSelectListener(new OnTagSelectListener() {
//            @Override
//            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
//
//            }
//        });
    }

    private void initRecyclerView(){
        castAdapter = new CastAdapter(getApplicationContext());
        castAdapter.setRecyclerViewListener(innerObjectKeyRecyclerViewListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3, GridLayoutManager.VERTICAL, false);
        rcCastList.setLayoutManager(gridLayoutManager);
        rcCastList.setItemAnimator(new DefaultItemAnimator());
        rcCastList.setHasFixedSize(true);
        rcCastList.setLayoutManager(gridLayoutManager);
        rcCastList.setAdapter(castAdapter);
    }

    private void getMovieDetail(int id){

        //檢查網路
        if(!Util.isInternetOn(getBaseContext())){
            Util.showShortToast(getBaseContext(),R.string.internet_loss);
            return;
        }

        Retrofit retrofit = WebUtil.getRetrofitRxJava(Constants.DEFAULT_TIME_OUT,Constants.URL_MOVIE_BASE, true,"getMovieList");
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
//        messageAdapter.setLastKeyForPosition("");//抓全部就允許移動list position
//        requestInterface.discoverMovie(Constants.MOVIE_API_KEY,Constants.VALUE_SORT_RELEASE_DATE_DESC,false,Util.getCurrentDateText(),1)
        requestInterface.movieDetail(id,Constants.MOVIE_API_KEY, getResources().getConfiguration().locale.toLanguageTag(),Constants.APPEND_TO_REQUEST_CREDITS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<MovieDetailResponse>(MovieDetailActivity.this, getMovieDetailObserverListener));
    }
    private String getRuntimeText(int minutes){
        if(minutes > 0){
           return  Util.getString(getApplicationContext(),R.string.runtime,String.valueOf(minutes));
        }else{
            return  Util.getString(getApplicationContext(),R.string.runtime_unavailable);
        }
    }
    class GetMovieDetailObserverListener implements ObserverOnNextListener<MovieDetailResponse> {
        @Override
        public void onNext(MovieDetailResponse resp) {
            try{
                //server回應為空
                if(resp == null){
                    Util.showShortToast(getBaseContext(),R.string.system_is_busy);
                    return;
                }

                Picasso.with(getApplicationContext())
                        .load(String.format("%s%s", Constants.URL_POSTER_BASE,resp.getPoster_path()))
                        .placeholder(R.drawable.empty_icon)
                        .error(R.drawable.btn_no_entry)
                        .into(ivPoster);
                tvTitle.setText(resp.getTitle());
                tvOverView.setText(resp.getOverview());
                tvReleaseDate.setText(Util.getString(getApplicationContext(),R.string.release_date,String.valueOf(resp.getRelease_date())));
                tvRunTime.setText(getRuntimeText(resp.getRuntime()));
                tvRate.setText(Util.getString(getApplicationContext(),R.string.rate,String.valueOf(resp.getVote_average())));
                int px = Util.dpToPx(getApplicationContext(),105.3f);
                ivRateLine.getLayoutParams().width = (int)(px * resp.getVote_average() / 10);
                tagAdapter.onlyAddAll(resp.getGenres());
                tagAdapter.notifyDataSetChanged();
                if(resp.getCredits().cast.isEmpty()){
                    tvCastLabel.setText("");
                    castAdapter.clearData();
                }else{
                    tvCastLabel.setText(R.string.cast);
                    castAdapter.setData(resp.getCredits().getCast());
                }
                castAdapter.notifyDataSetChanged();

            }catch (Exception e){
                Util.showShortToast(getBaseContext(),R.string.system_is_busy);
                LogHelper.logToCloud(String.format("getPushList_fail |%s", e.getMessage()));
                LogHelper.reportCrash(e);
            }
        }

        @Override
        public void onError() {

        }


    }
    class InnerObjectKeyRecyclerViewListener implements ObjectKeyRecyclerViewListener {

        @Override
        public void onItemClick(int key, Object bean) {
        }

        @Override
        public boolean onItemLongClick(int key, Object bean) {
//            DialogHandler.showAlertDialog(getActivity(),simpleListItem.getContent(),null);
//            LuxgenUtils.showShortToast(getContext(),String.format("%s long clicked",simpleListItem.getTitle()));
            return true;
        }
    }
}
