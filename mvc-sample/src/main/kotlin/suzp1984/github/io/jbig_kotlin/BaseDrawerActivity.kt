package suzp1984.github.io.jbig_kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import butterknife.ButterKnife

/**
 * Created by moses on 11/11/2016.
 */
abstract class BaseDrawerActivity : AppCompatActivity() {

    override fun setContentView(layoutId: Int) {
        super.setContentView(R.layout.activity_base_drawer)

        val parent : FrameLayout = findViewById(R.id.content) as FrameLayout
        LayoutInflater.from(this).inflate(layoutId, parent, true)

        injectView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu : Menu) : Boolean {
        menuInflater.inflate(R.menu.menu_base_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        val id = item.itemId
        if (id == R.id.action_about) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun injectView() {
        ButterKnife.bind(this)
    }
}