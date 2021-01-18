package com.example.tatvsoftpractical.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tatvsoftpractical.repositroy.UserDataRespository
import com.example.tatvsoftpractical.response.UserDataResponse

class UserdataViewModel(application: Application) : AndroidViewModel(application) {
    private var articleResponseLiveData: LiveData<UserDataResponse>? = null
    val articleRepository: UserDataRespository = UserDataRespository()
    fun getUserLiveData(offset: String?): LiveData<UserDataResponse>? {
        articleResponseLiveData =
            articleRepository.getUserData(offset, "10") as LiveData<UserDataResponse>
        return articleResponseLiveData
    }
}