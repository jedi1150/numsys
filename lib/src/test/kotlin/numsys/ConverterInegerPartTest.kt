package numsys

import numsys.model.NumberSystem
import numsys.model.Radix
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ConverterInegerPartTest {
    private val nsBin = NumberSystem("10000000", Radix.BIN)
    private val nsOct = NumberSystem("200", Radix.OCT)
    private val nsDec = NumberSystem("128", Radix.DEC)
    private val nsHex = NumberSystem("80", Radix.HEX)
    private val ns36 = NumberSystem("3K", Radix(36))

    @Test
    fun binToBinTest() = assertEquals(NumSys.convert(value = nsBin, toRadix = Radix.BIN), nsBin)

    @Test
    fun binToOctTest() = assertEquals(NumSys.convert(value = nsBin, toRadix = Radix.OCT), nsOct)

    @Test
    fun binToDecTest() = assertEquals(NumSys.convert(value = nsBin, toRadix = Radix.DEC), nsDec)

    @Test
    fun binToHexTest() = assertEquals(NumSys.convert(value = nsBin, toRadix = Radix.HEX), nsHex)

    @Test
    fun binTo36Test() = assertEquals(NumSys.convert(value = nsBin, toRadix = Radix(36)), ns36)

    @Test
    fun octToBinTest() = assertEquals(NumSys.convert(value = nsOct, toRadix = Radix.BIN), nsBin)

    @Test
    fun octToOctTest() = assertEquals(NumSys.convert(value = nsOct, toRadix = Radix.OCT), nsOct)

    @Test
    fun octToDecTest() = assertEquals(NumSys.convert(value = nsOct, toRadix = Radix.DEC), nsDec)

    @Test
    fun octToHexTest() = assertEquals(NumSys.convert(value = nsOct, toRadix = Radix.HEX), nsHex)

    @Test
    fun octTo36Test() = assertEquals(NumSys.convert(value = nsOct, toRadix = Radix(36)), ns36)

    @Test
    fun decToBinTest() = assertEquals(NumSys.convert(value = nsDec, toRadix = Radix.BIN), nsBin)

    @Test
    fun decToOctTest() = assertEquals(NumSys.convert(value = nsDec, toRadix = Radix.OCT), nsOct)

    @Test
    fun decToDecTest() = assertEquals(NumSys.convert(value = nsDec, toRadix = Radix.DEC), nsDec)

    @Test
    fun decToHexTest() = assertEquals(NumSys.convert(value = nsDec, toRadix = Radix.HEX), nsHex)

    @Test
    fun decTo36Test() = assertEquals(NumSys.convert(value = nsDec, toRadix = Radix(36)), ns36)

    @Test
    fun hexToBinTest() = assertEquals(NumSys.convert(value = nsHex, toRadix = Radix.BIN), nsBin)

    @Test
    fun hexToOctTest() = assertEquals(NumSys.convert(value = nsHex, toRadix = Radix.OCT), nsOct)

    @Test
    fun hexToDecTest() = assertEquals(NumSys.convert(value = nsHex, toRadix = Radix.DEC), nsDec)

    @Test
    fun hexToHexTest() = assertEquals(NumSys.convert(value = nsHex, toRadix = Radix.HEX), nsHex)

    @Test
    fun hexTo36Test() = assertEquals(NumSys.convert(value = nsHex, toRadix = Radix(36)), ns36)

    @Test
    fun ns36ToBinTest() = assertEquals(NumSys.convert(value = ns36, toRadix = Radix.BIN), nsBin)

    @Test
    fun ns36ToOctTest() = assertEquals(NumSys.convert(value = ns36, toRadix = Radix.OCT), nsOct)

    @Test
    fun ns36ToDecTest() = assertEquals(NumSys.convert(value = ns36, toRadix = Radix.DEC), nsDec)

    @Test
    fun ns36ToHexTest() = assertEquals(NumSys.convert(value = ns36, toRadix = Radix.HEX), nsHex)

    @Test
    fun ns36To36Test() = assertEquals(NumSys.convert(value = ns36, toRadix = Radix(36)), ns36)
}
