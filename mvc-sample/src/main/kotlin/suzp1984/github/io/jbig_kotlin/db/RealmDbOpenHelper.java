package suzp1984.github.io.jbig_kotlin.db;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import suzp1984.github.io.jbig_kotlin.realmobj.JbigItem;

/**
 * Created by suzhenxi on 11/28/2016.
 */

public class RealmDbOpenHelper implements DataBaseHelper {

    private Realm mRealm;

    @Inject
    public RealmDbOpenHelper(Realm realm) {
        mRealm = realm;
    }

    @Override
    public List<byte[]> getJbigs() {
        List<byte[]> ret = new ArrayList<>();

        RealmQuery<JbigItem> query = mRealm.where(JbigItem.class);
        RealmResults<JbigItem> results = query.findAll();

        for (JbigItem item : results) {
            ret.add(item.getJbig());
        }

        return ret;
    }

    @Override
    public byte[] getJbig(int position) {
        RealmQuery<JbigItem> query = mRealm.where(JbigItem.class);
        RealmResults<JbigItem> results = query.findAll();

        JbigItem item = results.get(position);

        if (item != null) {
            return item.getJbig();
        }

        return null;
    }

    @Override
    public void put(byte[] jbig) {
        Log.d("ReamlDb", "save realm object.");

        mRealm.beginTransaction();

        JbigItem item = mRealm.createObject(JbigItem.class);
        item.setTag("PaintView");
        item.setJbig(jbig);

        mRealm.commitTransaction();
    }

    @Override
    public void put(Collection<byte[]> jbigs) {
        for(byte[] item : jbigs) {
            put(item);
        }
    }

    @Override
    public void delete(byte[] jbig) {
        RealmQuery<JbigItem> query = mRealm.where(JbigItem.class);
        RealmResults<JbigItem> results = query.findAll();

        for (JbigItem item : results) {
            if (item.getJbig() == jbig) {
                mRealm.beginTransaction();
                item.deleteFromRealm();
                mRealm.commitTransaction();
                break;
            }
        }
    }

    @Override
    public void delete(int position) {
        RealmQuery<JbigItem> query = mRealm.where(JbigItem.class);
        RealmResults<JbigItem> results = query.findAll();

        results.remove(position);

        mRealm.commitTransaction();
    }

    @Override
    public void deleteAll() {
        RealmQuery<JbigItem> query = mRealm.where(JbigItem.class);
        RealmResults<JbigItem> results = query.findAll();

        results.clear();
        mRealm.commitTransaction();
    }
}
