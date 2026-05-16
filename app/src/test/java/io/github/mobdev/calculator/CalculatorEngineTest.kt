package io.github.mobdev.calculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CalculatorEngineTest {

    private lateinit var calc: CalculatorEngine

    @Before
    fun setUp() {
        calc = CalculatorEngine()
    }

    @Test
    fun initialDisplay_isZero() {
        assertEquals("0", calc.display)
    }

    @Test
    fun addition_works() {
        calc.appendDigit('2')
        calc.appendDigit('5')
        calc.setOperator('+')
        calc.appendDigit('3')
        calc.equals()
        assertEquals("28", calc.display)
    }

    @Test
    fun subtraction_works() {
        calc.appendDigit('1')
        calc.appendDigit('0')
        calc.setOperator('-')
        calc.appendDigit('4')
        calc.equals()
        assertEquals("6", calc.display)
    }

    @Test
    fun multiplication_works() {
        calc.appendDigit('6')
        calc.setOperator('*')
        calc.appendDigit('7')
        calc.equals()
        assertEquals("42", calc.display)
    }

    @Test
    fun division_works() {
        calc.appendDigit('8')
        calc.setOperator('/')
        calc.appendDigit('2')
        calc.equals()
        assertEquals("4", calc.display)
    }

    @Test
    fun divisionByZero_showsError() {
        calc.appendDigit('5')
        calc.setOperator('/')
        calc.appendDigit('0')
        assertTrue(calc.equals())
        assertEquals("Ошибка", calc.display)
    }

    @Test
    fun clear_resets() {
        calc.appendDigit('9')
        calc.clear()
        assertEquals("0", calc.display)
    }

    @Test
    fun state_restore_preservesDisplay() {
        calc.appendDigit('1')
        calc.appendDigit('2')
        calc.setOperator('+')
        val saved = calc.saveState()

        val other = CalculatorEngine()
        other.restore(saved)
        assertEquals("12", other.display)
        assertEquals('+', other.saveState().operator)
    }
}
