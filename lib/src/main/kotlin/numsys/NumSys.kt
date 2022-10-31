package numsys

import numsys.model.NumberSystem
import numsys.model.Radix
import numsys.utils.COMMA
import numsys.utils.NS_DELIMITER
import java.math.RoundingMode
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.math.pow

object NumSys {
    @JvmStatic
    private var FRACTIONAL_LENGTH = 12

    fun convert(value: NumberSystem, toRadix: Radix): NumberSystem {
        print("converter: $value")

        check(value.radix.value > 2 || value.radix.value < 36 || toRadix.value > 2 || value.radix.value < 36) {
            "Radix must be greater than 2 and smaller than 36"
        }

        if (value.radix == toRadix) {
            return NumberSystem(value = value.value, radix = value.radix)
        }

        return execute(value, toRadix)
    }

    private fun execute(value: NumberSystem, toRadix: Radix): NumberSystem {
        Logger.getGlobal().log(Level.FINEST, "Converter::convert: from radix ${value.radix.value} to radix ${toRadix.value}")

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
        Logger.getGlobal().log(Level.FINEST, "Converter::toDec")

        val valueWithoutComma = value.value.replace("[,.]".toRegex(), "")
        val integerPart = value.value.split("[,.]".toRegex())[0]

        val dec = valueWithoutComma.toCharArray().mapIndexed { index, char ->
            (char.toString().toInt(value.radix.value).toString(10).toBigDecimal() * value.radix.value.toDouble().pow(integerPart.toCharArray().size - (index + 1)).toBigDecimal()).setScale(12, RoundingMode.HALF_UP)
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
        Logger.getGlobal().log(Level.FINEST, "Converter::fromDec")

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
