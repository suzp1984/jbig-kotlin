package suzp1984.github.io.jbig_kotlin

import android.app.Application
import android.os.StrictMode
import butterknife.ButterKnife

/**
 * Created by moses on 11/11/2016.
 */

class JbigApplication : Application() {

    override fun onCreate() {

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())

            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())

            ButterKnife.setDebug(true)
        }

        super.onCreate()
    }
}
