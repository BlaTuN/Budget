package com.example.budget.di

import com.example.budget.service.MainApi
import com.example.budget.utilit.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun providerMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

fun providerOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

fun providerRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String,
    moshi: Moshi
): MainApi = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
    .create(MainApi::class.java)

val apiModule = module {
    single { Constant.BASE_URL }
    single { providerMoshi() }
    single { providerOkHttpClient() }
    single { providerRetrofit(okHttpClient = get(), baseUrl = get(), moshi = get()) }
}