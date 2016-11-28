package suzp1984.github.io.jbig_kotlin.db;

import java.util.Collection;
import java.util.List;

/**
 * Created by suzhenxi on 11/28/2016.
 */

public interface DataBaseHelper {
    List<byte[]> getJbigs();

    byte[] getJbig(int position);

    void put(byte[] jbig);

    void put(Collection<byte[]> jbigs);

    void delete(byte[] jbig);
    void delete(int position);

    void deleteAll();
}
