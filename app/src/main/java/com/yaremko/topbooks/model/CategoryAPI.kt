package com.yaremko.topbooks.model

import io.reactivex.Single
import retrofit2.http.GET

interface CategoryAPI {

    @GET("names.json?api-key=ndxBivGsGwm4hgpKGjAQvypWcxU5JMK6")
    fun getCategories() : Single<Names>
}