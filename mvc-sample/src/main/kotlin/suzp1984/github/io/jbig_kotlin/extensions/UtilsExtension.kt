package suzp1984.github.io.jbig_kotlin.extensions

/**
 * Created by suzhenxi on 11/29/2016.
 */
fun ByteArray.byteArray2HexString() : String? {
    if (this.size == 0) {
        return null
    }

    val sbd = StringBuilder()
    for (b in this) {
        var tmp = Integer.toHexString(0xFF and (b as Int))
        if (tmp.length < 2) {
            tmp = "0" + tmp
        }

        sbd.append(tmp)
    }

    return sbd.toString()
}
