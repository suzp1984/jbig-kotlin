package suzp1984.github.io.jbig_kotlin.controller

import com.squareup.otto.Subscribe

import javax.inject.Inject
import javax.inject.Singleton

import dagger.internal.Preconditions
import suzp1984.github.io.jbig_kotlin.display.IDisplay
import suzp1984.github.io.jbig_kotlin.states.ApplicationState
import suzp1984.github.io.jbig_kotlin.states.JbigDbState

/**
 * Created by suzhenxi on 11/28/2016.
 */

class MainController(state: ApplicationState, controller: JbigController) :
        BaseUiController<MainController.MainControllerUi, MainController.MainControllerUiCallback>() {

    enum class TabItem {
        PAINT_TAB, DECODER_TAB
    }

    private val mApplicationState: ApplicationState
    val jbigController: JbigController

    init {

        mApplicationState = Preconditions.checkNotNull(state, "Applicatin cannot be null.")
        jbigController = Preconditions.checkNotNull(controller, "JbigController cannot be null.")
    }

    override fun createUiCallbacks(ui: MainControllerUi): MainControllerUiCallback {
        return object : MainControllerUiCallback {
            override fun onTabItemSelected(item: TabItem) {
                val display = getDisplay()

                if (display != null) {
                    showUiItem(display, item)
                    display.closeDrawerLayout()
                }
            }
        }
    }

    override fun onUiAttached(ui: MainControllerUi) {

    }

    override fun onUiDetached(ui: MainControllerUi) {

    }

    override fun populateUi(ui: MainControllerUi) {
        // do nothing?
    }

    override fun onInited() {
        mApplicationState.registerForEvents(this)

        jbigController.init()
    }

    override fun onSuspended() {
        jbigController.suspend()

        mApplicationState.unregisterForEvents(this)
    }

    override fun setDisplay(display: IDisplay) {
        super.setDisplay(display)

        jbigController.setDisplay(display)
    }

    @Subscribe
    fun onJbigDataAdd(event: JbigDbState.JbigDbAddEvent) {
        // populate Decoder Tab
        populateUis()
    }

    fun attachDisplay(display: IDisplay) {
        Preconditions.checkNotNull(display, "display is null")

        setDisplay(display)
    }

    fun detachDisplay(display: IDisplay) {
        Preconditions.checkNotNull(display, "display is null")
        if (getDisplay() !== display) {
            throw RuntimeException("display is not attached")
        }

        // setDisplay(null)
    }

    private fun showUiItem(display: IDisplay, item: TabItem) {
        Preconditions.checkNotNull(display, "IDisplay cannot be null")
        Preconditions.checkNotNull(item, "TabItem cannot be null.")

        when (item) {
            MainController.TabItem.PAINT_TAB -> display.showPaintUi()
            MainController.TabItem.DECODER_TAB -> display.showDecoderUi()
            else -> {
            }
        }

        // set selected TabItem
    }

    interface MainControllerUi : BaseUiController.Ui<MainControllerUiCallback>

    interface MainControllerUiCallback {
        fun onTabItemSelected(item: TabItem)
    }
}
