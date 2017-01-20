package suzp1984.github.io.jbig_kotlin.db

/**
 * Created by suzhenxi on 11/28/2016.
 */

interface DataBaseHelper {
    val jbigs: List<ByteArray>

    fun getJbig(position: Int): ByteArray?

    fun put(jbig: ByteArray)

    fun put(jbigs: Collection<ByteArray>)

    fun delete(jbig: ByteArray)
    fun delete(position: Int)

    fun deleteAll()
}
