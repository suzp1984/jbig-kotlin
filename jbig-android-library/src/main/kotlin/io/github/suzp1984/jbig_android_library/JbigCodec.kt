package io.github.suzp1984.jbig_android_library

import android.graphics.Bitmap

/**
 * Created by moses on 11/11/2016.
 */
interface JbigCodec {
    fun encode(bitmaps: Array<Bitmap>) : ByteArray?
    fun decode(data: ByteArray): Array<Bitmap>?
}