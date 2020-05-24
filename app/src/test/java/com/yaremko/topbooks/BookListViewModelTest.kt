package com.yaremko.topbooks

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yaremko.topbooks.di.DaggerBookListViewModelComponent
import com.yaremko.topbooks.model.BookApiService
import com.yaremko.topbooks.model.Books
import com.yaremko.topbooks.model.ListName
import com.yaremko.topbooks.model.SearchResults
import com.yaremko.topbooks.viewmodel.BookListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class BookListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val key = "hardcover-fiction"

    @Mock
    lateinit var apiService: BookApiService

    val application = Mockito.mock(Application::class.java)
    val listView = BookListViewModel(application)


    @Test
    fun getBookListSuccess(){
        val book = Books(null,null,null,null,"test description", "test title","test author", null, null,null)
        val array = arrayListOf<Books>(book)
        val searchResult = SearchResults("Hardcover Fiction", "hardcover-fiction", null, null, null,null,array)
        val listName = ListName(null,null,null,null,searchResult)
        val testSingle: Single<ListName> = Single.just(listName)

        Mockito.`when`(apiService.getBookList(key)).thenReturn(testSingle)

        listView.getBookList(key)

        Assert.assertEquals(1, listView.listName.value?.searchResult?.bookList?.size)
        Assert.assertEquals(false, listView.loading.value)
        Assert.assertEquals(false, listView.loadError.value)
    }

    @Test
    fun getBookListFailure() {
        val tetSingle = Single.error<ListName>(Throwable())

        Mockito.`when`(apiService.getBookList(key)).thenReturn(tetSingle)

        listView.getBookList(key)

        Assert.assertEquals(null, listView.listName.value)
        Assert.assertEquals(true, listView.loadError.value)
        Assert.assertEquals(false, listView.loading.value)
    }


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        DaggerBookListViewModelComponent.builder()
            .bookApiModule(BookListApiModuleTest(apiService))
            .build()
            .inject(listView)
    }

    @Before
    fun setupRxSchedulers(){
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }
}