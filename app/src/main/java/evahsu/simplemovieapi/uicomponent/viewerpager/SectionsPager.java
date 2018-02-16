package evahsu.simplemovieapi.uicomponent.viewerpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import evahsu.simplemovieapi.R;
import evahsu.simplemovieapi.intf.FragmentCallback;


/**
 * Created by evahsu on 2017/1/14.
 */

public class SectionsPager extends Fragment {
//    @IntDef({A,B})
//        public  @interface FragementType{}
    public static final int FRAGEMENT_TYPE_MOVIE_LIST = 0;
    public static final int FRAGEMENT_TYPE_MOVIE_DETAIL = 1;

    public static final String FRAGMENT_TYPE = "fragment_type";
    private View mRootView;
    private int mType;

    public FragmentCallback getFragmentListener() {
        return mFragmentListener;
    }

    public void setFragmentListener(FragmentCallback fragmentListener) {
        this.mFragmentListener = fragmentListener;
    }

    private String mPageTitle = "";
    private FragmentCallback mFragmentListener;
    public SectionsPager() {}

    public static SectionsPager newInstance(int type, String pageTitle, FragmentCallback fragmentListener) {
        SectionsPager fragment = new SectionsPager();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_TYPE, type);
        fragment.setArguments(args);
        fragment.setPageTitle(pageTitle);
        fragment.setFragmentListener(fragmentListener);
        return fragment;
    }

    public View getRootView(){
        return mRootView;
    }
    public String getPageTitle(){
        return mPageTitle;
    }
    public void setPageTitle(String pageTitle){
        mPageTitle = pageTitle;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mFragmentListener == null){
            return;
        }
        mFragmentListener.onActivityCreated(savedInstanceState, mType);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = (TextView) rootView.findViewById(R.eventId.section_label);
        mType = getArguments().getInt(FRAGMENT_TYPE);
        switch(mType){
            case FRAGEMENT_TYPE_MOVIE_LIST:
                mRootView = inflater.inflate(R.layout.pager_movie_list, container, false);
                break;
            case FRAGEMENT_TYPE_MOVIE_DETAIL:
                mRootView = inflater.inflate(R.layout.pager_movie_detail, container, false);
                break;
            default:
                mRootView = inflater.inflate(R.layout.fragment_empty, container, false);
        }
//            textView.setText(getString(R.string.section_format, getArguments().getInt(FRAGMENT_TYPE)));
        return mRootView;
    }

}
