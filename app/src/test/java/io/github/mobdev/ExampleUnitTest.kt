package io.github.mobdev

import org.junit.Test

import org.junit.Assert.assertFalse
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun packageName_isNotExample() {
        assertFalse("io.github.mobdev".contains("com.example"))
    }
}