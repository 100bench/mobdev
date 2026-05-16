package io.github.mobdev.calculator

/**
 * Простая логика калькулятора: два операнда и один оператор.
 * Все вычисления в [Double].
 */
class CalculatorEngine {

    data class State(
        val display: String,
        val operand: Double?,
        val operator: Char?,
        val awaitingOperand: Boolean,
    )

    var display: String = "0"
        private set

    private var operand: Double? = null
    private var operator: Char? = null
    private var awaitingOperand = false

    fun restore(state: State) {
        display = state.display
        operand = state.operand
        operator = state.operator
        awaitingOperand = state.awaitingOperand
    }

    fun saveState(): State = State(display, operand, operator, awaitingOperand)

    fun clear() {
        display = "0"
        operand = null
        operator = null
        awaitingOperand = false
    }

    fun appendDigit(digit: Char) {
        require(digit in '0'..'9')
        if (awaitingOperand || display == "0") {
            display = digit.toString()
            awaitingOperand = false
        } else {
            display += digit
        }
    }

    fun appendDot() {
        if (awaitingOperand) {
            display = "0."
            awaitingOperand = false
            return
        }
        if (!display.contains('.')) {
            display += "."
        }
    }

    fun setOperator(op: Char) {
        require(op in OPERATORS)
        val value = display.toDoubleOrNull() ?: return

        if (operand != null && operator != null && !awaitingOperand) {
            val result = apply(operand!!, value, operator!!)
            if (result == null) {
                display = "Ошибка"
                operand = null
                operator = null
                awaitingOperand = true
                return
            }
            operand = result
            display = format(result)
        } else {
            operand = value
        }

        operator = op
        awaitingOperand = true
    }

    fun equals(): Boolean {
        val op = operator ?: return false
        val left = operand ?: return false
        val right = display.toDoubleOrNull() ?: return false
        if (awaitingOperand) return false

        val result = apply(left, right, op)
        if (result == null) {
            display = "Ошибка"
            operand = null
            operator = null
            awaitingOperand = true
            return true
        }
        display = format(result)
        operand = null
        operator = null
        awaitingOperand = true
        return true
    }

    private fun apply(a: Double, b: Double, op: Char): Double? = when (op) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '/' -> if (b == 0.0) null else a / b
        else -> null
    }

    companion object {
        val OPERATORS = setOf('+', '-', '*', '/')

        fun format(value: Double): String {
            if (value.isNaN() || value.isInfinite()) return "Ошибка"
            val text = if (value == value.toLong().toDouble()) {
                value.toLong().toString()
            } else {
                value.toString()
            }
            return text
        }
    }
}
