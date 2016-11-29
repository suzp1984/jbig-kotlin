package io.github.suzp1984.jbig_android_library

import android.graphics.Bitmap

/**
 * Created by moses on 11/11/2016.
 */
class JniJbigCodec : JbigCodec{
    init {
        System.loadLibrary("jbigkit")
    }

    override fun encode(bitmaps: Array<Bitmap>): ByteArray? {
        return encodeNative(bitmaps)
    }

    override fun decode(data: ByteArray): Array<Bitmap>? {
        return decodeNative(data)
    }

    external fun encodeNative(bitmaps: Array<Bitmap>): ByteArray?
    external fun decodeNative(data: ByteArray): Array<Bitmap>?
}