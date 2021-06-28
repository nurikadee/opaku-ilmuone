package com.nurika.opaku.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nurika.opaku.data.repository.AppRepository
import com.nurika.opaku.di.Injection
import com.nurika.opaku.view.home.HomeViewModel
import com.nurika.opaku.view.user.UserViewModel

class ViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UserViewModel::class.java) -> UserViewModel(repository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}