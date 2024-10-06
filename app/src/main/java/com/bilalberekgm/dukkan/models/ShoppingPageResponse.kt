package com.bilalberekgm.dukkan.models

data class ShoppingPageResponse(
    val content:List<Shop>,
    val totalPages:Int,
    val totalElements:Int,
    val size:Int,
    val number:Int
)

data class Shop(
    val shopId:Int,
    val name:String,
    val description:String,
    val contactNumber:String,
    val rating:String,
    val category:String,
    val openingHours:String
)
