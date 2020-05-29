package com.example.fetchimagesfromimgur

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class OkHttpRequest(client: OkHttpClient) {

    internal var client = OkHttpClient()

    init {
        this.client = client
    }

    fun get(url: String, authorization : String, callback: Callback): Call {
        val request = Request.Builder()
            .url(url)
            .header("Authorization",authorization)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }

}