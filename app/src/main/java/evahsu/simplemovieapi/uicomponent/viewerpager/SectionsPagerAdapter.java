package evahsu.simplemovieapi.uicomponent.viewerpager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * Created by evahsu on 2017/1/14.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private List<SectionsPager> mFragmentList;
    public SectionsPagerAdapter(FragmentManager fm, List<SectionsPager> list) {
        super(fm);
        this.mFragmentList = list;
    }

    @Override
    public SectionsPager getItem(int position) {
        return mFragmentList.get(position);
    }

    public void setData(List<SectionsPager> list){
        mFragmentList = list;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentList.get(position).getPageTitle();
    }

}
