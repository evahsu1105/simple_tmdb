package evahsu.simplemovieapi.ui;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import evahsu.simplemovieapi.R;
import evahsu.simplemovieapi.debug.LogHelper;
import evahsu.simplemovieapi.intf.FragmentCallback;
import evahsu.simplemovieapi.intf.ObjectKeyRecyclerViewListener;
import evahsu.simplemovieapi.uicomponent.viewerpager.LockableViewPager;
import evahsu.simplemovieapi.uicomponent.viewerpager.SectionsPager;
import evahsu.simplemovieapi.uicomponent.viewerpager.SectionsPagerAdapter;
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

public class MainActivity extends AppCompatActivity {

    public static final int PAGE_INDEX_SETTING_MESSAGE_LIST = 0;
    public static final int PAGE_INDEX_SETTING_MESSAGE_DETAIL = 1;
    protected int mCurrentPageIndex = 0;
    private LockableViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private List<SectionsPager> mFragmentList;
    private MovieAdapter movieAdapter;
    private InnerObjectKeyRecyclerViewListener innerObjectKeyRecyclerViewListener = new InnerObjectKeyRecyclerViewListener();
    private GetMovieListObserverListener getPushListObserverListener = new GetMovieListObserverListener();
    private Result readingResult;
    @Nullable @BindView(R.id.movie_list_recyclerView) RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }
    private void initFragment(){
        mFragmentList = new ArrayList<>();
        mViewPager = ButterKnife.findById(MainActivity.this,R.id.movie_list_main_pager);
        mFragmentList.add(newMovieListPage());
        mFragmentList.add(newMovieDetailPage());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    private SectionsPager newMovieListPage(){
        return SectionsPager.newInstance(SectionsPager.FRAGEMENT_TYPE_MOVIE_LIST,"", new FragmentCallback() {
            @Override
            public void onActivityCreated(@Nullable Bundle savedInsanceState, int fragmentType) {
                ButterKnife.bind(MainActivity.this);
                initRecyclerView();
            }
        });
    }

    private SectionsPager newMovieDetailPage(){
        return SectionsPager.newInstance(SectionsPager.FRAGEMENT_TYPE_MOVIE_DETAIL,"", new FragmentCallback() {
            @Override
            public void onActivityCreated(@Nullable Bundle savedInsanceState, int fragementType) {
                ButterKnife.bind(MainActivity.this);
            }
        });
    }
    private void initRecyclerView(){
        movieAdapter = new MovieAdapter(getApplicationContext());
        movieAdapter.setRecyclerViewListener(innerObjectKeyRecyclerViewListener);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
    }

    public void getMovieList(){
        //檢查網路
        if(!Util.isInternetOn(getBaseContext())){
            Util.showShortToast(getBaseContext(),R.string.internet_loss);
            return;
        }

        Retrofit retrofit = WebUtil.getRetrofitRxJava(Constants.DEFAULT_TIME_OUT,Constants.URL_MOVIE_BASE, true,"getMovieList");
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
//        messageAdapter.setLastKeyForPosition("");//抓全部就允許移動list position
        requestInterface.discoverMovie(Constants.MOVIE_API_KEY,Constants.VALUE_SORT_RELEASE_DATE_DESC,false,Util.getCurrentDateText(),1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<MovieDiscoverResponse>(MainActivity.this, getPushListObserverListener));


    }
    private void switchPage(int fromIndex, int toIndex, boolean goHome){
        Handler handler;
        switch (fromIndex){
            case PAGE_INDEX_SETTING_MESSAGE_LIST:
                break;
            case PAGE_INDEX_SETTING_MESSAGE_DETAIL:
                break;
        }
        switch (toIndex){
            case PAGE_INDEX_SETTING_MESSAGE_LIST:
                break;
            case PAGE_INDEX_SETTING_MESSAGE_DETAIL:
//                handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(readingPushInfoRecord == null){
//                            return;
//                        }
//                        tvMessageDetailProvider.setText(readingPushInfoRecord.getProvider());
//                        tvMessageDetailTitle.setText(readingPushInfoRecord.getTitle());
//                        tvMessageDetailDate.setText(readingPushInfoRecord.getShowPushDate());
//                        tvMessageDetailContent.setText(readingPushInfoRecord.getContents());
//                    }
//                }, Constants.CHANGE_CONTENT_MS);
                break;
            default:
                super.onBackPressed();
        }
        setPagerCurrentItem(toIndex);
    }

    private void setPagerCurrentItem(int index){
        mViewPager.setCurrentItem(index);
        mCurrentPageIndex = index;
    }
    class InnerObjectKeyRecyclerViewListener implements ObjectKeyRecyclerViewListener {

        @Override
        public void onItemClick(int key, Object bean) {
            if(bean == null){
                LogHelper.logToCloud("",String.format("ReadModeItemClick null:%s",key));
                return;
            }
            readingResult = (Result) bean;
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
                movieAdapter.addData(resp.results);
                movieAdapter.notifyDataSetChanged();
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
}
