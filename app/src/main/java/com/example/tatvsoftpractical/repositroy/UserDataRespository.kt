package com.example.tatvsoftpractical.repositroy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tatvsoftpractical.response.UserDataResponse
import com.example.tatvsoftpractical.retrofit.ApiRequest
import com.example.tatvsoftpractical.retrofit.RetrofitRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataRespository {

    private val TAG: String =
        UserDataRespository::class.java.getSimpleName()

    val request = RetrofitRequest.buildService(ApiRequest::class.java)

    fun getUserData(
        offset: String?,
        limit: String?
    ): LiveData<UserDataResponse?>? {
        val data: MutableLiveData<UserDataResponse?> = MutableLiveData<UserDataResponse?>()

        request.getUserData(offset, limit)
            ?.enqueue(object : Callback<UserDataResponse?> {
                override fun onResponse(
                    call: Call<UserDataResponse?>,
                    response: Response<UserDataResponse?>
                ) {
                    Log.d(
                        TAG,
                        "onResponse response:: $response"
                    )
                    if (response.body() != null) {
                        data.setValue(response.body())
                        Log.d(
                            TAG,
                            "articles total result:: " + response.body()
                        )
                        Log.d(
                            TAG,
                            "articles size:: " + response.body()!!.data!!.usersdata!!.size
                        )
                        Log.d(
                            TAG,
                            "articles title pos 0:: " + response.body()!!.data!!.usersdata?.get(0)
                            !!.name
                        )
                    }
                }

                override fun onFailure(
                    call: Call<UserDataResponse?>,
                    t: Throwable
                ) {
                    data.setValue(null)
                }
            })
        return data
    }
}