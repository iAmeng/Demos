package imeng.cordinatorlayoutdemo.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import imeng.utilslib4android.utils.L;

/**
 * 一个各种View 都可以使用的 Behavior
 * @project: Demos
 * @Author : Administrator
 * @Date : 2016/3/10 9:44
 * @Version:
 */
public class FancyBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final String TAG = "FancyBehavior";
    public FancyBehavior() {

    }

    public FancyBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, V child, MotionEvent ev) {
        L.e(TAG, "onTouchEvent");


        return super.onTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, V child, MotionEvent ev) {
        L.e(TAG, "onInterceptTouchEvent");
        child.performClick();
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, V child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        L.e(TAG, "onMeasureChild");

        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int
            nestedScrollAxes) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dxConsumed, int dyConsumed, int
            dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }
}

