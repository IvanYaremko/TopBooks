package com.yaremko.topbooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yaremko.topbooks.model.CategoryApiService
import com.yaremko.topbooks.model.Names
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CategoryListViewModel(application: Application) : AndroidViewModel(application) {


    private val disposable = CompositeDisposable()
    private var apiService = CategoryApiService()

    val categories by lazy { MutableLiveData<Names>() }


     fun getCategories() {
        disposable.add(
            apiService.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Names>() {
                    override fun onSuccess(t: Names) {
                        categories.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

}