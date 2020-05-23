package com.yaremko.topbooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yaremko.topbooks.model.BookApiService
import com.yaremko.topbooks.model.ListName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class BookListViewModel(application: Application) : AndroidViewModel(application) {

    private val disposable = CompositeDisposable()
    private val apiService = BookApiService()

    val listName by lazy { MutableLiveData<ListName>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val loadError by lazy { MutableLiveData<Boolean>() }

    fun getBookList(listName: String){
        loading.value = true

        disposable.add(
            apiService.getBookList(listName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ListName>(){
                    override fun onSuccess(t: ListName) {
                        this@BookListViewModel.listName.value = t
                        loading.value = false
                        loadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        loadError.value = true
                    }
                } )
        )
    }
}