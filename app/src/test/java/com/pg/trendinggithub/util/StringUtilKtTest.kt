package com.pg.trendinggithub.util

import org.junit.Test

internal class StringUtilKtTest {

    @Test
    fun `returns true for contains ignore `() {
        val result = "ABCD".containsIgnoreCase("bcd")
        assert(result)
    }
}
