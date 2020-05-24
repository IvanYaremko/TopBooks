package com.yaremko.topbooks.di

import com.yaremko.topbooks.model.BookAPI
import com.yaremko.topbooks.model.BookApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class BookApiModule {

    @Provides
    fun provideBookListApi() : BookAPI{
        return  Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/books/v3/lists/current/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(BookAPI::class.java)
    }

    @Provides
    open fun provideBookApiService(): BookApiService {
        return BookApiService()
    }
}