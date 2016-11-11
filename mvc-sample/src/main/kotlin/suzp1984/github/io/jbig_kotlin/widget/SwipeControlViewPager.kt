package suzp1984.github.io.jbig_kotlin.widget

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import suzp1984.github.io.jbig_kotlin.R

/**
 * Created by moses on 11/11/2016.
 */

class SwipeControlViewPager : ViewPager {

    private var mSwipeable = true

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.SwipeControlViewPager)

        try {
            mSwipeable = a.getBoolean(R.styleable.SwipeControlViewPager_swipeable, true)
        } finally {
            a.recycle()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return mSwipeable && super.onInterceptTouchEvent(ev)
    }

    fun setSwipeable(swipeable: Boolean) {
        mSwipeable = swipeable
    }
}