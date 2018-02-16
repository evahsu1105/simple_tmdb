package evahsu.simplemovieapi.uicomponent.viewerpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by evahsu on 2017/1/14.
 */
public class LockableViewPager extends ViewPager {

    private boolean mSwipeLocked = true;


    public boolean isEnableAnimation() {
        return mEnableAnimation;
    }

    public void setEnableAnimation(boolean mEnableAnimation) {
        this.mEnableAnimation = mEnableAnimation;
    }

    private boolean mEnableAnimation = false;

    public LockableViewPager(Context context) {
        super(context);
    }

    public LockableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isSwipeLocked() {
        return mSwipeLocked;
    }

    public void setSwipeLocked(boolean mSwipeLocked) {
        this.mSwipeLocked = mSwipeLocked;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,mEnableAnimation);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, (mEnableAnimation && smoothScroll));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !mSwipeLocked && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return !mSwipeLocked && super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return !mSwipeLocked && super.canScrollHorizontally(direction);
    }

}
