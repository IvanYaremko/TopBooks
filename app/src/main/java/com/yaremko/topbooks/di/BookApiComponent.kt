package com.yaremko.topbooks.di

import com.yaremko.topbooks.model.BookApiService
import dagger.Component

@Component(modules = [BookApiModule::class])
interface BookApiComponent {

    fun inject(service: BookApiService)
}