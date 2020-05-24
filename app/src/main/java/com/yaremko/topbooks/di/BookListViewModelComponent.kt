package com.yaremko.topbooks.di

import com.yaremko.topbooks.viewmodel.BookListViewModel
import dagger.Component

@Component(modules = [BookApiModule::class])
interface BookListViewModelComponent {
    fun inject(service: BookListViewModel)
}