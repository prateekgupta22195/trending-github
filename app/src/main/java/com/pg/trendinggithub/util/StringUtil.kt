package com.pg.trendinggithub.util

import java.util.regex.Pattern

fun String.containsIgnoreCase(string: String): Boolean {
    return Pattern.compile(Pattern.quote(string), Pattern.CASE_INSENSITIVE).matcher(this)
        .find()
}
