package io.github.suzp1984.jbig_android_library

/**
 * Created by moses on 11/11/2016.
 */
class JbigCodecFactory {
    enum class CODEC {
        JNI_CODEC
    }

    fun getJbigCodec(codec: CODEC): JbigCodec? {
        when (codec) {
            CODEC.JNI_CODEC -> return JniJbigCodec()
        }

        return null
    }
}