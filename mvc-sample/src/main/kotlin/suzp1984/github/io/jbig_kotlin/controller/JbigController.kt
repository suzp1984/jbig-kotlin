package suzp1984.github.io.jbig_kotlin.controller

import android.graphics.Bitmap
import android.util.Log
import com.squareup.otto.Subscribe
import io.github.suzp1984.jbig_android_library.JbigCodecFactory
import suzp1984.github.io.jbig_kotlin.states.JbigDbState
import suzp1984.github.io.jbig_kotlin.extensions.byteArray2HexString

/**
 * Created by suzhenxi on 11/29/2016.
 */
class JbigController : BaseUiController<JbigController.JbigControllerUi, JbigController.JbigUiCallback>() {

    private lateinit var mJbigDbState: JbigDbState

    override fun onInited() {
        mJbigDbState.registerForEvents(this)
    }

    override fun onSuspended() {
        mJbigDbState.unregisterForEvents(this)
    }

    override fun createUiCallbacks(ui: JbigControllerUi): JbigUiCallback {
        return object : JbigUiCallback {
            override fun encodeBitmap(bitmap: Bitmap) {
                val bitmaps = arrayOf<Bitmap>(bitmap)

                val jbigCodec = JbigCodecFactory.getJbigCodec(JbigCodecFactory.CODEC.JNI_CODEC)

                if (jbigCodec != null) {
                    val jbigData = jbigCodec.encode(bitmaps)

                    if (jbigData != null) {
                        mJbigDbState.putJbig(jbigData)
                    }

                    Log.e("Encode", jbigData?.byteArray2HexString())
                }
            }

            override fun delete(position: Int) {

            }

            override fun add(jbig: ByteArray) {
                mJbigDbState.putJbig(jbig)
            }
        }
    }

    override fun onUiAttached(ui: JbigControllerUi) {
    }

    override fun onUiDetached(ui: JbigControllerUi) {
    }

    override fun populateUi(ui: JbigControllerUi) {
        Log.e("JbigController", "popularUi")
        if (ui is JbigEncoderUi) {
            populateJbigEncoderUi(ui)
        } else if (ui is JbigDecoderUi) {
            populateJbigDecoderUi(ui)
        }
    }

    @Subscribe
    fun onJbigDataAdd(event: JbigDbState.JbigDbAddEvent) {
        Log.e("JbigController", "onJbigDataAdd")
        populateUis()
    }

    private fun populateJbigDecoderUi(ui: JbigDecoderUi) {
        Log.e("JbigController", "populateUi JbigDecoderUi")
        val jbigCodec = JbigCodecFactory.getJbigCodec(JbigCodecFactory.CODEC.JNI_CODEC)

        if (jbigCodec != null) {
            for (jbig in mJbigDbState.jbigDbs) {
                val bms = jbigCodec.decode(jbig)
                if (bms != null) {
                    ui.showBitmaps(bms)
                }
            }
        }
    }

    private fun populateJbigEncoderUi(ui: JbigEncoderUi) {
        // nothing, pass
    }


    interface JbigControllerUi : BaseUiController.Ui<JbigUiCallback>

    interface JbigUiCallback {
        fun encodeBitmap(bitmap: Bitmap)
        fun delete(position: Int)
        fun add(jbig: ByteArray)
    }

    interface JbigDecoderUi : JbigControllerUi {
        fun showJbig(jbig: ByteArray)
        fun showBitmaps(bitmaps: Array<Bitmap>)
    }

    interface JbigEncoderUi : JbigControllerUi
}