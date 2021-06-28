package com.nurika.opaku.view.home

import androidx.lifecycle.ViewModel
import com.nurika.opaku.data.repository.AppRepository

class HomeViewModel(private val repository: AppRepository) : ViewModel() {

    fun catalogList() = repository.getCatalog()
}