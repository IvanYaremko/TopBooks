package com.yaremko.topbooks.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BookAPI {

    @GET("{listName}.json?api-key=ndxBivGsGwm4hgpKGjAQvypWcxU5JMK6")
    fun getBookList(@Path("listName") listName: String) : Single<ListName>

}