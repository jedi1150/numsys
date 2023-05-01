package numsys

import numsys.model.NumberSystem
import numsys.model.Radix
import org.junit.Test
import kotlin.test.assertFailsWith

class ConverterIllegalValueTest {
    private val emptyValue = ""
    private val blankValue = "    "
    private val dotValue = "."
    private val commaValue = ","
    private val dotCommaValue = ".,"
    private val mixedValue = "-,. "

    private val nsBin = NumberSystem(emptyValue, Radix.BIN)
    private val nsOct = NumberSystem(blankValue, Radix.OCT)
    private val nsDec = NumberSystem(dotValue, Radix.DEC)
    private val nsHex = NumberSystem(mixedValue, Radix.HEX)

    @Test
    fun binToBinTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsBin, toRadix = Radix.BIN) }
    }

    @Test
    fun binToOctTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsBin, toRadix = Radix.OCT)}
    }

    @Test
    fun binToDecTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsBin, toRadix = Radix.DEC)}
    }

    @Test
    fun binToHexTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsBin, toRadix = Radix.HEX)}
    }

    @Test
    fun octToBinTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsOct, toRadix = Radix.BIN)}
    }

    @Test
    fun octToOctTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsOct, toRadix = Radix.OCT)}
    }

    @Test
    fun octToDecTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsOct, toRadix = Radix.DEC)}
    }

    @Test
    fun octToHexTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsOct, toRadix = Radix.HEX)}
    }

    @Test
    fun decToBinTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsDec, toRadix = Radix.BIN)}
    }

    @Test
    fun decToOctTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsDec, toRadix = Radix.OCT)}
    }

    @Test
    fun decToDecTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsDec, toRadix = Radix.DEC)}
    }

    @Test
    fun decToHexTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsDec, toRadix = Radix.HEX)}
    }

    @Test
    fun hexToBinTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsHex, toRadix = Radix.BIN)}
    }

    @Test
    fun hexToOctTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsHex, toRadix = Radix.OCT)}
    }

    @Test
    fun hexToDecTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsHex, toRadix = Radix.DEC)}
    }

    @Test
    fun hexToHexTest() {
        assertFailsWith(IllegalArgumentException::class) { NumSys.convert(value = nsHex, toRadix = Radix.HEX)}
    }

}
