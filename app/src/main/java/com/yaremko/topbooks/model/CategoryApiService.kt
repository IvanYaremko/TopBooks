package com.yaremko.topbooks.model


import com.yaremko.topbooks.di.DaggerCategoryApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CategoryApiService {


    @Inject
    lateinit var api: CategoryAPI

    init {
      DaggerCategoryApiComponent.create().inject(this)
    }

    fun getCategories(): Single<Names> {
        return api.getCategories()
    }
}