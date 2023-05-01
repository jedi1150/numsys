package numsys

import numsys.model.NumberSystem
import numsys.model.Radix
import numsys.utils.COMMA
import numsys.utils.Log
import numsys.utils.NS_DELIMITER
import java.util.Locale
import kotlin.math.pow

object NumSys {
    @JvmStatic
    private var FRACTIONAL_LENGTH = 12

    /**
     * Converts [NumberSystem] to another [NumberSystem].
     *
     * @param value Initial [NumberSystem].
     * @param toRadix Required [Radix] for result.
     * @return [NumberSystem]
     * @see NumberSystem
     * @see Radix
     */
    fun convert(value: NumberSystem, toRadix: Radix): NumberSystem {
        Log.info("convert $value to $toRadix")

        if (value.value.none { it.isLetterOrDigit() }) throw IllegalArgumentException("Noting to convert")
        if (value.radix == toRadix) return value

        return execute(value, toRadix)
    }

    /**
     * Converts [NumberSystem] to another [NumberSystem].
     *
     * @param value Required [Radix] for result.
     * @return [NumberSystem]
     * @see NumberSystem
     * @see Radix
     */
    fun NumberSystem.toRadix(value: Radix): NumberSystem = convert(this, value)

    private fun execute(value: NumberSystem, toRadix: Radix): NumberSystem {
        var minusBool = false
        if (value.value.contains("-")) {
            value.value = value.value.replace("-", "")
            minusBool = true
        }

        var result: NumberSystem

        if (value.radix != Radix.DEC) {
            result = toDec(value)
            if (toRadix != Radix.DEC) {
                result = fromDec(result, toRadix)
            }
        } else {
            result = fromDec(value, toRadix)
        }

        if (minusBool) {
            result.value = result.value.replaceRange(0, 0, "-")
        }
        return result
    }

    private fun toDec(value: NumberSystem): NumberSystem {
        val valueWithoutComma = value.value.replace("[,.]".toRegex(), "")
        val integerPart = value.value.split("[,.]".toRegex())[0]

        val dec = valueWithoutComma.toCharArray().mapIndexed { index, char ->
            (char.toString().toInt(value.radix.value).toString(10).toBigDecimal() * value.radix.value.toDouble().pow(integerPart.toCharArray().size - (index + 1)).toBigDecimal())
        }
        var result = dec.reduceRight { acc, decimal -> acc + decimal }.toString()    // Summing all chars

        // Pretty formatting
        while (result.length > 1 && result.contains("[,.]".toRegex()) && result.endsWith("0")) {
            result = result.substringBeforeLast("0")
        }

        while (result.length > 1 && result.contains("[,.]".toRegex()) && (result.endsWith(COMMA) || result.endsWith(NS_DELIMITER))) {
            result = result.split("[,.]".toRegex())[0]
        }

        return NumberSystem(value = result, radix = Radix.DEC)
    }

    private fun fromDec(value: NumberSystem, toRadix: Radix): NumberSystem {
        var result: String = value.value.split("[,.]".toRegex())[0].toBigInteger(10).toString(toRadix.value).uppercase(Locale.getDefault())
        if ((value.value.contains(COMMA) || value.value.contains(NS_DELIMITER)) && value.value.split("[,.]".toRegex())[1].isNotEmpty()) {
            var fractionalPart = value.value
            var i = 0
            var convertedFraction = ""
            while (i < FRACTIONAL_LENGTH) {
                if (fractionalPart.split("[,.]".toRegex())[1].toBigDecimal() == "0".toBigDecimal()) break
                fractionalPart = (".${fractionalPart.split("[,.]".toRegex())[1]}".toBigDecimal() * toRadix.value.toBigDecimal()).toString()
                i++
                convertedFraction += fractionalPart.split("[,.]".toRegex())[0].toBigInteger(10).toString(toRadix.value).uppercase(Locale.getDefault())
            }
            result += ".$convertedFraction"
        }

        // Pretty formatting
        while (result.length > 1 && result.contains("[,.]".toRegex()) && result.endsWith("0")) {
            result = result.substringBeforeLast("0")
        }

        return NumberSystem(value = result, radix = toRadix)
    }

}
