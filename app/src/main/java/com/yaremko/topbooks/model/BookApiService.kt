package com.yaremko.topbooks.model

import com.yaremko.topbooks.di.DaggerBookApiComponant
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class BookApiService {

//    private val api = Retrofit.Builder()
//        .baseUrl("https://api.nytimes.com/svc/books/v3/lists/current/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//        .create(BookAPI::class.java)

    @Inject
    lateinit var api: BookAPI

    init {
        DaggerBookApiComponant.create().inject(this)
    }

    fun getBookList(listName: String) : Single<ListName> {
        return api.getBookList(listName)
    }
}