package io.github.mobdev.calculator

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val engine = CalculatorEngine()

    val display: String
        get() = engine.display

    fun onDigit(digit: Char) = engine.appendDigit(digit)

    fun onDot() = engine.appendDot()

    fun onOperator(op: Char) = engine.setOperator(op)

    fun onEquals() = engine.equals()

    fun onClear() = engine.clear()

    fun saveState(): CalculatorEngine.State = engine.saveState()

    fun restoreState(state: CalculatorEngine.State) = engine.restore(state)
}
