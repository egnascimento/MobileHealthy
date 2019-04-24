package br.com.eduardo.healthy.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import java.util.concurrent.TimeUnit


class RetrofitAPI<T> {
    fun getClient(c: Class<T>) : T {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.26:3030")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkhttpClient())
            .build()

        return retrofit.create(c)
    }
}

fun getOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun getRecordAPI(): RecordAPI {
    return RetrofitAPI<RecordAPI>().getClient(RecordAPI::class.java)
}