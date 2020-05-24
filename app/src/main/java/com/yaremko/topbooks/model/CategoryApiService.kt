package com.yaremko.topbooks.model


import com.yaremko.topbooks.di.DaggerCategoryApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CategoryApiService {
//
//    private val api = Retrofit.Builder()
//        .baseUrl("https://api.nytimes.com/svc/books/v3/lists/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//        .create(CategoryAPI::class.java)

    @Inject
    lateinit var api: CategoryAPI

    init {
      DaggerCategoryApiComponent.create().inject(this)
    }

    fun getCategories(): Single<Names> {
        return api.getCategories()
    }
}