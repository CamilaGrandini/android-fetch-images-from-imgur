package com.example.fetchimagesfromimgur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response

import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var url = "https://api.imgur.com/3/gallery/search/?q=cats"
        var authorization = "Client-ID 1ceddedc03a5d71"

        var client = OkHttpClient()
        var request = OkHttpRequest(client)


        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        var catLinks  = ArrayList<String>()

        request.get(url,authorization, object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                val jsonObject = JSONObject(responseData)

                catLinks = GetLinks.get(jsonObject)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Request Failure.")
            }
        })

            catLinks.forEach {
                var imageView = ImageView(this)
                Glide.with(this).load(it).into(imageView)
                linearLayout.addView(imageView)
            }

    }
}
