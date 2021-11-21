package com.example.dxb.network


import android.util.Log
import com.example.dxb.application.Application

import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitClient {

        private val TAG = "RetrofitClient"
        private val BASE_URL = ""
         val IMAGE_BASE_URL = ""
        private var retrofit: Retrofit? = null
        private var retrofitRx: Retrofit? = null
        private val HEADER_CACHE_CONTROL = "Cache-Control"
        private val HEADER_PRAGMA = "Pragma"
        private val cacheSize = (5 * 1024 * 1024).toLong()

        private fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                val cache = Cache(Application.getInstance().cacheDir, cacheSize)
                val logging = HttpLoggingInterceptor()
                // set your desired log level
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
                    .connectTimeout(3, TimeUnit.MINUTES)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)//.cache(cache)

                httpClient.addInterceptor(httpLoggingInterceptor()!!)
//                httpClient.addInterceptor(offlineInterceptor()!!)
//                httpClient.addNetworkInterceptor(networkInterceptor()!!)
                retrofit = Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        private fun okHttpClient(): OkHttpClient? {
            return OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor(httpLoggingInterceptor()!!) // used if network off OR on
                .addNetworkInterceptor(networkInterceptor()!!) // only used when network is on
                .addInterceptor(offlineInterceptor()!!)
                .build()
        }

        private fun httpLoggingInterceptor(): HttpLoggingInterceptor? {
            val httpLoggingInterceptor= HttpLoggingInterceptor { message ->
                Log.d(TAG, " $message")
            }
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return httpLoggingInterceptor
        }

        private fun networkInterceptor(): Interceptor? {
            return Interceptor { chain ->
                Log.d(TAG, "network interceptor: called.")
                val response = chain.proceed(chain.request())
                val cacheControl: CacheControl = CacheControl.Builder()
                    .maxAge(15, TimeUnit.SECONDS)
                    .build()
                response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
        }

        private fun offlineInterceptor(): Interceptor? {
            return Interceptor { chain ->
                Log.d(TAG, "offline interceptor: called.")
                var request = chain.request()
                if (!Application.hasNetwork()) {
                    val cacheControl: CacheControl = CacheControl.Builder()
                        .maxStale(15, TimeUnit.SECONDS)
                        .build()
                    request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
                }
                chain.proceed(request)
            }
        }


        private fun cache(): Cache? {
            return Cache(File(Application.getInstance().getCacheDir(), "someIdentifier"), cacheSize)
        }


        fun getApi(): ApiService? {
            return getRetrofitInstance()!!.create(ApiService::class.java)
        }

        //-----------rx
        private fun getRetrofitInstanceRx(): Retrofit? {
            if (retrofitRx == null) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
                    .connectTimeout(3, TimeUnit.MINUTES)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                httpClient.addInterceptor(logging)
                retrofitRx = Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofitRx
        }

        fun getApiServiceRx(): ApiService? {
            return getRetrofitInstanceRx()!!.create(ApiService::class.java)
        }




}