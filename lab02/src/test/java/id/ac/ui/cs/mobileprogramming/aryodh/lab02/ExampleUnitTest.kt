package id.ac.ui.cs.mobileprogramming.aryodh.lab02

import android.content.Context
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun darkMessage_isCorrect() {
        val activity = MainActivity()
        assertEquals("Too Dark?", activity.nightMessage())
    }

    @Test
    fun lightMessage_isCorrect() {
        val activity = MainActivity()
        assertEquals("Too Bright?", activity.lightMessage())
    }
}