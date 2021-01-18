package com.example.tatvsoftpractical.model

import com.google.gson.annotations.SerializedName

class data {
    @SerializedName("users")
    public val usersdata: List<users>? = null
    @SerializedName("has_more")
    public val has_more: Boolean? = false

    class users {
        @SerializedName("name")
        public val name: String? = null

        @SerializedName("image")
        public val image: String? = null

        @SerializedName("items")
        public val items: List<String>? = null
    }
}