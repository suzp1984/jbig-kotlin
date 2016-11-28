package suzp1984.github.io.jbig_kotlin.realmobj;

import io.realm.RealmObject;

/**
 * Created by suzhenxi on 11/28/2016.
 */

public class JbigItem extends RealmObject {

    private String tag;

    private byte[] jbig;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setJbig(byte[] jbig) {
        this.jbig = jbig;
    }

    public String getTag() {
        return tag;
    }

    public byte[] getJbig() {
        return jbig;
    }
}
