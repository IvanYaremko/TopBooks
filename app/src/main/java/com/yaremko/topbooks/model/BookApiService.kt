package com.yaremko.topbooks.model


import com.yaremko.topbooks.di.DaggerBookApiComponent
import io.reactivex.Single
import javax.inject.Inject

class BookApiService {

    @Inject
    lateinit var api: BookAPI

    init {
        DaggerBookApiComponent.create().inject(this)
    }

    fun getBookList(listName: String) : Single<ListName> {
        return api.getBookList(listName)
    }
}