package suzp1984.github.io.jbig_kotlin.states;

import java.util.Collection;
import java.util.List;

/**
 * Created by suzhenxi on 11/28/2016.
 */

public interface JbigDbState extends BaseState {
    List<byte[]> getJbigDbs();
    byte[] getJbigAtPosition(int position);

    void putJbig(byte[] jbig);
    void putJbigs(Collection<byte[]> jbigs);
    void deleteJbig(byte[] jbig);

    public static class JbigDbAddEvent {
    }

    public static class JbigDbDeleteEvent {

    }
}
