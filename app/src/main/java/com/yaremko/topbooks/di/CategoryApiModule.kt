package com.yaremko.topbooks.di

import com.yaremko.topbooks.model.CategoryAPI
import com.yaremko.topbooks.model.CategoryApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class CategoryApiModule {

    @Provides
    fun provideCategoryApi(): CategoryAPI {
        return   Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/books/v3/lists/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CategoryAPI::class.java)
    }

    @Provides
    open fun provideCategoryApiService() : CategoryApiService {
        return CategoryApiService()
    }

}