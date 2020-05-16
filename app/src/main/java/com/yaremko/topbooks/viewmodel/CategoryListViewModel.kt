package com.yaremko.topbooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yaremko.topbooks.model.Results

class CategoryListViewModel(application: Application) : AndroidViewModel(application) {

    val categories by lazy { MutableLiveData<List<Results>>() }

    init {
        val category1 = Results("Testing", "T e s t", "weekly")
        val category2 = Results("Testing2", "T e s t2", "montly")

        categories.value = listOf(category1, category2)
    }

}