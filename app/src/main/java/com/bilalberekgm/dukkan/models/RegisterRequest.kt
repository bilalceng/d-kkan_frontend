package com.bilalberekgm.dukkan.models

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)