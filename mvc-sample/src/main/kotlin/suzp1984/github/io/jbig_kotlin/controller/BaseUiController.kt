package suzp1984.github.io.jbig_kotlin.controller

import java.util.Collections
import java.util.concurrent.CopyOnWriteArraySet

import dagger.internal.Preconditions

/**
 * Created by suzhenxi on 11/28/2016.
 */

internal abstract class BaseUiController<U : BaseUiController.Ui<UC>, UC> : BaseController() {

    private val mUis: MutableSet<U>
    private val mUnmodifiableUis: Set<U>

    init {
        mUis = CopyOnWriteArraySet<U>()
        mUnmodifiableUis = Collections.unmodifiableSet(mUis)
    }

    internal abstract fun createUiCallbacks(ui: U): UC
    internal abstract fun onUiAttached(ui: U)
    internal abstract fun onUiDetached(ui: U)
    internal abstract fun populateUi(ui: U)

    @Synchronized fun attachUi(ui: U) {
        Preconditions.checkNotNull(ui, "ui cannot be null")
        if (mUis.contains(ui)) {
            throw RuntimeException("Ui is already attached")
        }

        mUis.add(ui)
        ui.setCallback(createUiCallbacks(ui))

        if (isInited) {
            onUiAttached(ui)
            populateUi(ui)
        }
    }

    @Synchronized fun detachUi(ui: U) {
        Preconditions.checkNotNull(ui, "ui cannot be null")
        if (!mUis.contains(ui)) {
            throw RuntimeException("ui is not attached.")
        }

        onUiDetached(ui)
        ui.setCallback(null)

        mUis.remove(ui)
    }

    @Synchronized fun populateUis() {
        for (ui in mUis) {
            populateUi(ui)
        }
    }

    interface Ui<UC> {
        fun setCallback(callback: UC?)
        val isModal: Boolean
    }
}