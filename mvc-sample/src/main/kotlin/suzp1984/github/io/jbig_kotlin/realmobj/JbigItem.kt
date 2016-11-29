package suzp1984.github.io.jbig_kotlin.realmobj

import io.realm.RealmObject

/**
 * Created by suzhenxi on 11/28/2016.
 */

open class JbigItem : RealmObject() {

    var tag: String? = null

    var jbig: ByteArray? = null
}
