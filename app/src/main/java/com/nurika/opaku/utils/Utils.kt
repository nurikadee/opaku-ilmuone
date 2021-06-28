package com.nurika.opaku.utils

import java.text.NumberFormat
import java.util.*

object Utils {
    fun convertCurrency(number: Int): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("IDR")

        return format.format(number)
    }
}