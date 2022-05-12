package com.study.firebasecrash.Retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClientImage {

    /* var BASE_URL: String = "http://goprob.com/s4/api/";*/

   // var BASE_URL: String = "http://gcampus.khmhssalathiyur.com/";
   var BASE_URL: String = "http://elite-edu.arpnex.com/Api/"
   //var BASE_URL: String = "http://khmhss.corecampuz.com/";

    // var BASE_URL: String = "http://edu.arpnex.com/Staging/studentapp/";

    public val getClient: ApiInterface
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
}