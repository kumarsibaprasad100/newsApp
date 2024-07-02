package com.example.mycomposeapp.utils

import com.example.mycomposeapp.models.ArticlesItem

sealed class Resource<out T> {
    data class Success<out T>(val data: List<ArticlesItem?>?) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

}
