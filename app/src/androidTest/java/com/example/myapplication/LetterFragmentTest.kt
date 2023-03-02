package com.example.myapplication

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LetterFragmentTest {

    @Before
    fun setUp() {
        print("Hello")
        FragmentScenario.launchInContainer(LetterFragment::class.java)
    }

    @After
    fun tearDown() {
        print("Hello")
    }

    @Test
    fun testButtonDisabledAfterClicks() {
        onView(withId(R.id.buttonA))
            .perform(click())

        onView(withId(R.id.buttonA))
            .check(
                ViewAssertions.matches(
                    CoreMatchers.not(ViewMatchers.isEnabled())
                )
            )
    }
}