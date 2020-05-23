package com.yaremko.topbooks.di

import android.widget.ListView
import com.yaremko.topbooks.viewmodel.CategoryListViewModel
import dagger.Component
import dagger.Module

@Component(modules = [CategoryApiModule::class])
interface CategoryViewModelComponent {

    fun inject(viewModel: CategoryListViewModel)
}