package numsys.model

import numsys.NumSys

public class NumberSystem(public val value: String, public val radix: Radix)

public fun NumberSystem.pretty(): String = value.split(NumSys.Constants.Delimiter).joinToString(separator = NumSys.Constants.Delimiter.toString()) { part ->
    part.reversed().chunked(groupLength()).joinToString(NumSys.Constants.GroupSeparator.toString()).reversed()
}
