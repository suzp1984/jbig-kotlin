package suzp1984.github.io.jbig_kotlin.states

/**
 * Created by suzhenxi on 11/28/2016.
 */

interface JbigDbState : BaseState {
    val jbigDbs: List<ByteArray>
    fun getJbigAtPosition(position: Int): ByteArray

    fun putJbig(jbig: ByteArray)
    fun putJbigs(jbigs: Collection<ByteArray>)
    fun deleteJbig(jbig: ByteArray)

    class JbigDbAddEvent

    class JbigDbDeleteEvent
}
