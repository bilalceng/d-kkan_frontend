package com.bilalberekgm.dukkan.utils

import android.util.Log
import com.auth0.android.jwt.JWT
import java.util.Date

object JwtTokenParser {


    fun parseJwtToken(token: String): Boolean {
        return try {
            if (token.isNotEmpty()) {
                val jwt = JWT(token)
                Log.e("TAG", token)
                // Check if the token has expired
                val expiresAt = jwt.expiresAt
                expiresAt != null && expiresAt.after(Date())
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

}