package com.yaremko.topbooks

import com.yaremko.topbooks.di.BookApiModule
import com.yaremko.topbooks.model.BookApiService

class BookListApiModuleTest(val mockService: BookApiService) : BookApiModule() {
    override fun provideBookApiService(): BookApiService {
        return mockService
    }
}