package com.example.simpsonquotes.UI

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MainViewModleFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(MainViewModel::class.java)){
           return MainViewModel(context = context) as T
       }
        throw IllegalArgumentException("viewmodel illegal argument")

    }
}