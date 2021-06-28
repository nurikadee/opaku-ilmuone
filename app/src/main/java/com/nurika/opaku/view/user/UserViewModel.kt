package com.nurika.opaku.view.user

import androidx.lifecycle.ViewModel
import com.nurika.opaku.data.repository.AppRepository

class UserViewModel(private val repository: AppRepository) : ViewModel() {

    fun userList() = repository.getUser()

}