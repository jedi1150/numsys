package numsys.utils

import java.util.logging.Logger

internal object Log {
    private val logger = Logger.getLogger(this::class.simpleName)

    fun error(s: String) = logger.severe(s)
    fun warning(s: String) = logger.warning(s)
    fun info(s: String) = logger.info(s)
}
