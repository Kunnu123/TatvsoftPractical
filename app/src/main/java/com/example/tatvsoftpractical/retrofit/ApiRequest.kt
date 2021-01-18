package com.example.tatvsoftpractical.retrofit

import com.example.tatvsoftpractical.response.UserDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {

    @GET("api/users")
    fun getUserData(
        @Query("offset") query: String?,
        @Query("limit") apiKey: String?
    ): Call<UserDataResponse?>?
}