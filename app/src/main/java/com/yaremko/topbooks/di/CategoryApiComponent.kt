package com.yaremko.topbooks.di

import com.yaremko.topbooks.model.CategoryApiService
import dagger.Component

@Component(modules = [CategoryApiModule::class])
interface CategoryApiComponent {

    fun inject(service: CategoryApiService)
}