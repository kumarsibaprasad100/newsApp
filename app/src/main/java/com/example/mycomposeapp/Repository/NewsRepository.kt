package com.example.mycomposeapp.Repository

import com.example.mycomposeapp.models.NewsData
import com.example.mycomposeapp.network.ApiService
import com.example.mycomposeapp.utils.Resource
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun getNewsData(mData: String): Resource<NewsData> {
        return try {
            val response = apiService.getNews(mData, "631729be2bd84e5faf097775ebf4c461")
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()?.articles)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}