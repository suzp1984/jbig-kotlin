package suzp1984.github.io.jbig_kotlin.display

import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity

import suzp1984.github.io.jbig_kotlin.MainActivity

/**
 * Created by suzhenxi on 11/28/2016.
 */

class AndroidDisplay(private val mCompatActivity: AppCompatActivity,
                     private val mDrawerLayout: DrawerLayout?) : IDisplay {

    override fun showPaintUi() {
        val activity = mCompatActivity as MainActivity
        activity.selectPaintTab()
    }

    override fun showDecoderUi() {
        val activity = mCompatActivity as MainActivity
        activity.selectDecodeTab()
    }

    override fun showDrawerLayout() {
        if (mDrawerLayout != null && !mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
            mDrawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun closeDrawerLayout() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers()
        }
    }
}
