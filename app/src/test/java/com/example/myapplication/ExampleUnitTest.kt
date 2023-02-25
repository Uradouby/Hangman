package com.example.myapplication

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
}

class DisplayViewModelTest {

    @Test
    fun testGetHint() {
        val viewModel = DisplayViewModel()
        // set the view index to the first word in the list
        viewModel.viewIndex = 0
        val expectedHint = "fruit(apple)"
        assertEquals(expectedHint, viewModel.gethint())
    }

    @Test
    fun testGetWord() {
        val viewModel = DisplayViewModel()
        // set the view index to the first word in the list
        viewModel.viewIndex = 0
        val expectedWord = "_ _ _ _ _ "
        assertEquals(expectedWord, viewModel.getword())

        viewModel.press("A")
        val expectedWord2 = "A _ _ _ _ "
        assertEquals(expectedWord2, viewModel.getword())

        viewModel.press("P")
        val expectedWord3 = "A P P _ _ "
        assertEquals(expectedWord3, viewModel.getword())

        viewModel.press("L")
        viewModel.press("E")
        val expectedWord4 = "A P P L E "
        assertEquals(expectedWord4, viewModel.getword())
    }

    @Test
    fun testRenew() {
        val viewModel = DisplayViewModel()
        // set the view index to the first word in the list
        viewModel.viewIndex = 0
        viewModel.press("A")
        viewModel.press("P")
        viewModel.press("L")
        viewModel.press("E")
        viewModel.renew()
        assertFalse(viewModel.isPressed("A"))
        assertFalse(viewModel.isPressed("P"))
        assertFalse(viewModel.isPressed("L"))
        assertFalse(viewModel.isPressed("E"))
        assertEquals(0, viewModel.state)
        assertFalse(viewModel.success)
        assertNotEquals(0, viewModel.viewIndex)
    }

    @Test
    fun testIsPressed() {
        val viewModel = DisplayViewModel()
        assertFalse(viewModel.isPressed("A"))
        assertFalse(viewModel.isPressed("B"))
        assertFalse(viewModel.isPressed("C"))
        viewModel.press("A")
        assertTrue(viewModel.isPressed("A"))
        assertFalse(viewModel.isPressed("B"))
        assertFalse(viewModel.isPressed("C"))
    }

    @Test
    fun testPress() {
        val viewModel = DisplayViewModel()

        // Test pressing a correct letter
        viewModel.press("A")
        assertFalse(viewModel.success)
        assertEquals(0, viewModel.state)

        // Test pressing an incorrect letter
        viewModel.press("Z")
        assertFalse(viewModel.success)
        assertEquals(1, viewModel.state)

        // Test pressing the same letter again
        viewModel.press("A")
        assertFalse(viewModel.success)
        assertEquals(1, viewModel.state)

        // Test pressing all correct letters
        viewModel.press("P")
        viewModel.press("L")
        viewModel.press("E")
        assertTrue(viewModel.success)
        assertEquals(1, viewModel.state)
    }
}

