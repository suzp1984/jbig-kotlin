package suzp1984.github.io.jbig_kotlin.db

import android.util.Log

import java.util.ArrayList

import javax.inject.Inject

import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults
import suzp1984.github.io.jbig_kotlin.realmobj.JbigItem

/**
 * Created by suzhenxi on 11/28/2016.
 */

class RealmDbOpenHelper
@Inject
constructor(private val mRealm: Realm) : DataBaseHelper {

    override val jbigs: List<ByteArray>
        get() {
            val ret = ArrayList<ByteArray>()

            val query = mRealm.where(JbigItem::class.java)
            val results = query.findAll()

            for (item in results) {
                if (item.jbig != null) {
                    ret.add(item.jbig!!)
                }
            }

            return ret
        }

    override fun getJbig(position: Int): ByteArray? {
        val query = mRealm.where(JbigItem::class.java)
        val results = query.findAll()

        val item = results[position]

        if (item != null) {
            return item.jbig
        }

        return null
    }

    override fun put(jbig: ByteArray) {
        Log.d("ReamlDb", "save realm object.")

        mRealm.beginTransaction()

        val item = mRealm.createObject(JbigItem::class.java)
        item.tag = "PaintView"
        item.jbig = jbig

        mRealm.commitTransaction()
    }

    override fun put(jbigs: Collection<ByteArray>) {
        for (item in jbigs) {
            put(item)
        }
    }

    override fun delete(jbig: ByteArray) {
        val query = mRealm.where(JbigItem::class.java)
        val results = query.findAll()

        for (item in results) {
            if (item.jbig == jbig) {
                mRealm.beginTransaction()
                item.deleteFromRealm()
                mRealm.commitTransaction()
                break
            }
        }
    }

    override fun delete(position: Int) {
        val query = mRealm.where(JbigItem::class.java)
        val results = query.findAll()

        results.removeAt(position)

        mRealm.commitTransaction()
    }

    override fun deleteAll() {
        val query = mRealm.where(JbigItem::class.java)
        val results = query.findAll()

        results.clear()
        mRealm.commitTransaction()
    }
}
