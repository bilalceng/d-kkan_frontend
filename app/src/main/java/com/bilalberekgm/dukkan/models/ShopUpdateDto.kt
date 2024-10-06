package com.bilalberekgm.dukkan.models

data class ShopUpdateRequestResponseDto(
    val name: String,
    val description: String,
    val contactNumber: String,
    val email: String,
    val shopCategory: String,
    val openingHours: String,
    val website: String?
)
