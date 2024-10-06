package com.bilalberekgm.dukkan.service

import com.bilalberekgm.dukkan.models.LoginRequest
import com.bilalberekgm.dukkan.models.LoginResponse
import com.bilalberekgm.dukkan.models.RegisterRequest
import com.bilalberekgm.dukkan.models.RegisterResponse
import com.bilalberekgm.dukkan.models.ShopUpdateRequestResponseDto
import com.bilalberekgm.dukkan.models.ShoppingPageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("api/v1/auth/signup")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ):Response<RegisterResponse>

    @POST("api/v1/auth/signin")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("api/v1/main/shops/customer/search")
    suspend fun searchShops(
        @Query("name") name:String,
        @Query("page") page:Int,
        @Query("size") size:Int
    ):Response<ShoppingPageResponse>

    @GET("api/v1/main/shops/customer/rating")
    suspend fun filterShopsByRating(
        @Query("rating") rating:Double,
        @Query("page") page:Int,
        @Query("size") size:Int
    ):Response<ShoppingPageResponse>

    @GET("api/v1/main/shops/customer/topmessagenumber")
    suspend fun filterShopsByMessageNumber(
        @Query("messageNumber") messageNumber:Int,
        @Query("page") page:Int,
        @Query("size") size:Int
    ):Response<ShoppingPageResponse>

    @GET("api/v1/main/shops/customer/topproductnumber")
    suspend fun filterShopsByProductNumber(
        @Query("number") number:Int,
        @Query("page") page:Int,
        @Query("size") size:Int
    ):Response<ShoppingPageResponse>

    @DELETE("api/v1/main/shops/seller/{shopId}")
    suspend fun deleteShopById(
        @Path("shopId") shopId:Int
    ):Response<Void>

    @PUT("api/v1/main/shops/seller/updateshop/{shopId}")
    suspend fun updateShopById(
        @Path("shopId") shopId:Int,
        @Body shop: ShopUpdateRequestResponseDto
    ):Response<ShopUpdateRequestResponseDto>
    
}