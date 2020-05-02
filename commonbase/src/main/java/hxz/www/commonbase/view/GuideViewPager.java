package hxz.www.commonbase.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @date:2019-10-18
 * @author:andy 去掉左右滑动效果
 */
public class GuideViewPager extends ViewPager {

    public GuideViewPager(@NonNull Context context) {
        super(context);
    }

    public GuideViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}
