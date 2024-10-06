package com.bilalberekgm.dukkan.utils

import androidx.core.util.PatternsCompat

class EmailValidator {
    companion object{
        fun isValidEmail(email: String): Boolean {
            return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}