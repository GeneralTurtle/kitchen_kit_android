package com.guillaume.taffin.kitchenkit

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        temperatureType.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                celsiusButton.id -> temperatureSymbol.text = resources.getString(R.string.celsius_symbol)
                fahrenheitButton.id -> temperatureSymbol.text = resources.getString(R.string.fahrenheit_symbol)
            }
        }

        temperatureInput.setOnEditorActionListener { textView, _, _ ->
            val input =  temperatureInput.text.toString().toDouble()

            temperatureResult.text = when(temperatureType.checkedRadioButtonId) {
                celsiusButton.id -> formatFahrenheitResult(input)
                fahrenheitButton.id -> formatCelsiusResult(input)
                else -> "Invalid"
            }

            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(textView.windowToken, 0)

            true
        }

    }

    private fun toFahrenheit(inputValue: Double): Double = (inputValue * 9.0/5.0) + 32.0

    private fun toCelsius(inputValue: Double): Double = (inputValue - 32.0) * 5.0/9.0

    private fun formatResult(result: Double, symbol: String): String = "Result : $result $symbol"

    private fun formatFahrenheitResult(inputValue: Double): String {
        val result = toFahrenheit(inputValue)
        return formatResult(result, resources.getString(R.string.fahrenheit_symbol))
    }
    private fun formatCelsiusResult(inputValue: Double): String {
        val result = toCelsius(inputValue)
        return formatResult(result, resources.getString(R.string.celsius_symbol))
    }

}