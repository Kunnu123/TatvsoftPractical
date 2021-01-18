package com.example.tatvsoftpractical.response

import com.example.tatvsoftpractical.model.data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserDataResponse {
    @SerializedName("status")
    @Expose
    public val status: Boolean? = false

    @SerializedName("message")
    @Expose
    public val message: String? = null

    @SerializedName("data")
    @Expose
    public val data: data? = null
}