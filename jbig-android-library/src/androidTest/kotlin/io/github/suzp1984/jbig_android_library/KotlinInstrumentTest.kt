package io.github.suzp1984.jbig_android_library

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals

/**
 * Created by moses on 11/11/2016.
 */
@RunWith(AndroidJUnit4::class)
class KotlinInstrumentTest {
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("io.github.suzp1984.jbig_android_library.test", appContext.packageName)
    }
}