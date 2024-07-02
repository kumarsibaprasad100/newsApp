package com.example.mycomposeapp.network

import com.example.mycomposeapp.models.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getNews(@Query("q")data: String,@Query("apiKey")key : String): Response<NewsData>

}