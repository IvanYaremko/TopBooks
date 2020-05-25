package com.yaremko.topbooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yaremko.topbooks.di.DaggerCategoryViewModelComponent
import com.yaremko.topbooks.model.CategoryApiService
import com.yaremko.topbooks.model.Names
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CategoryListViewModel(application: Application) : AndroidViewModel(application) {

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var apiService: CategoryApiService

    val categories by lazy { MutableLiveData<Names>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val loadError by lazy { MutableLiveData<Boolean>() }

    init {
        DaggerCategoryViewModelComponent.create().inject(this)
    }

     fun getCategories() {

        loading.value = true // sets progress bar to true while api data is collected
        disposable.add(
            apiService.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Names>() {
                    override fun onSuccess(t: Names) {
                        categories.value = t
                        loading.value = false // sets the progress bar to false once the api is retrieved
                        loadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                    }
                })
        )
    }

}