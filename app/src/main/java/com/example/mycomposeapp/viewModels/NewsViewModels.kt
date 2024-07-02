package com.example.mycomposeapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposeapp.Repository.NewsRepository
import com.example.mycomposeapp.models.NewsData
import com.example.mycomposeapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModels @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val _data = MutableLiveData<Resource<NewsData>>()
    val data: LiveData<Resource<NewsData>> = _data

    fun fetchData(mData: String) {
        viewModelScope.launch {
            _data.value = newsRepository.getNewsData(mData)
        }
    }

}