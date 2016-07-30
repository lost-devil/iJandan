package com.wyy.ijandan.behavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yayun.wei on 2016/7/30.
 */
public class BottomScrollHideBehavior extends CoordinatorLayout.Behavior{

    private static final int ANIM_DURATION = 250;

    private ObjectAnimator mAnimator;

    public BottomScrollHideBehavior() {
        super();
    }

    public BottomScrollHideBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       View child, View directTargetChild, View target,
                                       int nestedScrollAxes) {
        //We have to declare interest in the scroll to receive further events
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (dy > 0) {
            hide(child);
        } else if (dy < 0) {
            show(child);
        }
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        if (velocityY > 0) {
            hide(child);
        } else if (velocityY < 0) {
            show(child);
        }
        return false;
    }

    private void show(View child) {
        if (mAnimator != null) {
            mAnimator.cancel();
            mAnimator = null;
        }
        mAnimator = ObjectAnimator
                .ofFloat(child, View.TRANSLATION_Y, 0f)
                .setDuration(ANIM_DURATION);
        mAnimator.start();
    }

    private void hide(View child) {
        if (mAnimator != null) {
            mAnimator.cancel();
            mAnimator = null;
        }
        mAnimator = ObjectAnimator
                .ofFloat(child, View.TRANSLATION_Y, child.getHeight())
                .setDuration(ANIM_DURATION);
        mAnimator.start();
    }
}
