package com.bilalberekgm.dukkan.utils
object UserNameParser{
    fun parseName(input: String): Pair<String, String> {
        // Split the input string by spaces
        val nameParts = input.trim().split("\\s+".toRegex())

        // If there's at least two parts, treat the first as first name and the rest as last name
        val firstName = nameParts.getOrNull(0)?.trim() ?: ""
        val lastName = nameParts.drop(1).joinToString(" ").trim()

        return Pair(firstName, lastName)
    }
}

