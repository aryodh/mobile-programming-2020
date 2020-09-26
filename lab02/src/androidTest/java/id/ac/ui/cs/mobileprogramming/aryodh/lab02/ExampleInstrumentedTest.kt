package id.ac.ui.cs.mobileprogramming.aryodh.lab02

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

import android.widget.TextView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Rule
import androidx.test.ext.junit.rules.ActivityScenarioRule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testHelloWorldExist() {
        onView(ViewMatchers.withId(R.id.mainText))
            .check(ViewAssertions.matches(ViewMatchers.withText("Hello World!")))
    }

    @Test
    fun testAboutMeTitleExist() {
        onView(ViewMatchers.withId(R.id.aboutMeTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText("About Me")))
    }

    @Test
    fun testAboutMeContentExist() {
        onView(ViewMatchers.withId(R.id.aboutMeContent))
            .check(ViewAssertions.matches(ViewMatchers.withText("Curios and organized person who has passion in technology, great listener, enjoy working with a lot of different people, and also a creative thinker.")))
    }

    @Test
    fun testDarkModeTitleExist() {
        onView(ViewMatchers.withId(R.id.darkModeTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText("Too Dark?")))
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("id.ac.ui.cs.mobileprogramming.aryodh.lab02", appContext.packageName)
    }

}