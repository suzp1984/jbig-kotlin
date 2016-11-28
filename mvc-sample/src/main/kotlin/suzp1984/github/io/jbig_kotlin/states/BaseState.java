package suzp1984.github.io.jbig_kotlin.states;

import suzp1984.github.io.jbig_kotlin.controller.MainController;

/**
 * Created by suzhenxi on 11/28/2016.
 */

public interface BaseState {

    MainController.TabItem getSelectedTabItem();

    void setSelectedTabItem(MainController.TabItem item);

    void registerForEvents(Object object);

    void unregisterForEvents(Object object);
}
