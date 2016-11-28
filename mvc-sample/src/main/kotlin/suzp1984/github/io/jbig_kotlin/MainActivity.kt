package suzp1984.github.io.jbig_kotlin

import android.os.Bundle

import butterknife.BindView
import suzp1984.github.io.jbig_kotlin.widget.SwipeControlViewPager

class MainActivity : BaseDrawerActivity() {

    @BindView(R.id.viewpager)
    lateinit var viewPager: SwipeControlViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }

    fun selectPaintTab() {

    }

    fun selectDecodeTab() {

    }
}
