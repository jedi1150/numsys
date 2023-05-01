package numsys.model

@JvmInline
value class Radix(val value: Int) {

    init {
        check(value > 2 || value < 36) {
            "Radix must be greater than 2 and smaller than 36"
        }
    }

    companion object {
        val BIN = Radix(2)
        val OCT = Radix(8)
        val DEC = Radix(10)
        val HEX = Radix(16)
    }
}

private val radixes: List<Radix> = Array(36) { radix -> Radix(radix + 1) }.filter { radix -> !arrayOf(Radix(1)).contains(radix) }
private val groupByThreeNumbers: List<Radix> = radixes.filter { listOf(Radix(3), Radix(7), Radix.OCT, Radix(9), Radix.DEC, Radix(11), Radix(12), Radix(13), Radix(14), Radix(15)).contains(it) }
private val groupByFourNumbers: List<Radix> = radixes.filter { !listOf(Radix(3), Radix(7), Radix.OCT, Radix(9), Radix.DEC, Radix(11), Radix(12), Radix(13), Radix(14), Radix(15)).contains(it) }

fun Radix.groupLength(): Int = when (this) {
    in groupByThreeNumbers -> 3
    in groupByFourNumbers -> 4
    else -> 3
}

fun NumberSystem.groupLength(): Int = this.radix.groupLength()
