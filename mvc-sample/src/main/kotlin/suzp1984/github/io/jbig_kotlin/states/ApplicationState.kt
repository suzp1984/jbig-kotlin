package suzp1984.github.io.jbig_kotlin.states

import com.squareup.otto.Bus

import java.util.ArrayList

import javax.inject.Inject

import dagger.internal.Preconditions
import suzp1984.github.io.jbig_kotlin.controller.MainController
import suzp1984.github.io.jbig_kotlin.db.DataBaseHelper

/**
 * Created by suzhenxi on 11/28/2016.
 */

class ApplicationState
@Inject
constructor(eventBus: Bus, helper: DataBaseHelper) : BaseState, JbigDbState {

    private val mEventBus: Bus
    private val mJbigList = ArrayList<ByteArray>()
    private val mDbHelper: DataBaseHelper

    override var selectedTabItem: MainController.TabItem? = null

    init {
        mEventBus = Preconditions.checkNotNull(eventBus, "EventBus cannot be null")
        mDbHelper = Preconditions.checkNotNull(helper, "DataBaseHelper cannot be null")

        mJbigList.addAll(mDbHelper.jbigs)
    }

    override val jbigDbs: List<ByteArray>
        get() = mJbigList

    override fun getJbigAtPosition(position: Int): ByteArray {
        return mJbigList[position]
    }

    override fun putJbig(jbig: ByteArray) {
        mDbHelper.put(jbig)
        mJbigList.add(jbig)
        mEventBus.post(JbigDbState.JbigDbAddEvent())
    }

    override fun putJbigs(jbigs: Collection<ByteArray>) {
        mDbHelper.put(jbigs)
        mJbigList.addAll(jbigs)
        mEventBus.post(JbigDbState.JbigDbAddEvent())
    }

    override fun deleteJbig(jbig: ByteArray) {
        // TODO. delete item.
    }

    override fun registerForEvents(`object`: Any) {
        mEventBus.register(`object`)
    }

    override fun unregisterForEvents(`object`: Any) {
        mEventBus.unregister(`object`)
    }
}

