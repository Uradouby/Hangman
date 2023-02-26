package com.example.myapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import org.junit.After


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.myapplication", appContext.packageName)
    }
}

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>
    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun testButtonClick() {
        val viewModel = DisplayViewModel()
        viewModel.viewIndex = 0

        // press a correct letter and check the hidden word
        viewModel.press("A")
        onView(withId(R.id.display_container))
            .check(matches(withText("_PP__")))

        // press an incorrect letter and check the state
        viewModel.press("Z")

        // press a correct letter and check the hidden word
        viewModel.press("L")
        onView(withId(R.id.display_container))
            .check(matches(withText("_PPL_")))

        // press another correct letter and check the hidden word and success status
        viewModel.press("E")
        onView(withId(R.id.display_container))
            .check(matches(withText("APPLE")))
    }
}
