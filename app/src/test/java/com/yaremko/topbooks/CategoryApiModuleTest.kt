package com.yaremko.topbooks

import com.yaremko.topbooks.di.CategoryApiModule
import com.yaremko.topbooks.model.CategoryApiService

class CategoryApiModuleTest(val mockService: CategoryApiService) : CategoryApiModule(){
    override fun provideCategoryApiService(): CategoryApiService {
        return mockService
    }
}