package suzp1984.github.io.jbig_kotlin

import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : BaseDrawerActivity() {

    @BindView(R.id.hello_world)
    lateinit var helloWorld : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        helloWorld.text = "Hello Again !!"
    }
}
