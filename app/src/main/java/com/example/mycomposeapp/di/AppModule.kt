package com.example.mycomposeapp.di

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton // If you want to provide a singleton instance
//    fun provideViewModelFactory(application: Application): ViewModelProvider.Factory {
//        return ViewModelFactory(application, SavedStateHandle())
//    }
}
