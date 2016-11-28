package suzp1984.github.io.jbig_kotlin.states

import suzp1984.github.io.jbig_kotlin.controller.MainController

/**
 * Created by suzhenxi on 11/28/2016.
 */

interface BaseState {

    var selectedTabItem: MainController.TabItem?

    fun registerForEvents(`object`: Any)

    fun unregisterForEvents(`object`: Any)
}
