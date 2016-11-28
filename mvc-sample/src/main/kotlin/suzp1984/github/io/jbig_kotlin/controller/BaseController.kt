package suzp1984.github.io.jbig_kotlin.controller

import suzp1984.github.io.jbig_kotlin.display.IDisplay

/**
 * Created by suzhenxi on 11/28/2016.
 */

internal abstract class BaseController {
    protected var mDisplay: IDisplay? = null

    var isInited: Boolean = false
        private set

    fun init() {
        if (isInited == true) {
            throw RuntimeException("Already inited")
        }

        isInited = true
        onInited()
    }

    fun suspend() {
        if (isInited == false) {
            throw RuntimeException("Not Inited")
        }

        onSuspended()
        isInited = false
    }

    open fun setDisplay(display: IDisplay) {
        mDisplay = display
    }

    open fun getDisplay(): IDisplay? {
        return mDisplay
    }

    internal abstract fun onInited()
    internal abstract fun onSuspended()
}
