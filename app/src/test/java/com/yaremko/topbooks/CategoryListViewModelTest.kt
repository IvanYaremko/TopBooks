package com.yaremko.topbooks

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yaremko.topbooks.di.DaggerCategoryViewModelComponent
import com.yaremko.topbooks.model.CategoryApiService
import com.yaremko.topbooks.model.Names
import com.yaremko.topbooks.model.Result
import com.yaremko.topbooks.viewmodel.CategoryListViewModel
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

class CategoryListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: CategoryApiService

    val application  = Mockito.mock(Application::class.java)
    val listViewModel = CategoryListViewModel(application)

    @Test
    fun getCategoriesSuccess() {

        val result = Result("test list", "test name", "test encode", null, null,null)
        val resultList = arrayListOf<Result>(result)
        val name = Names(null, null, null, resultList )
        val testSingle: Single<Names> = Single.just(name)

        Mockito.`when`(apiService.getCategories()).thenReturn(testSingle)

        listViewModel.getCategories()

        Assert.assertEquals(1, listViewModel.categories.value?.results?.size)
        Assert.assertEquals(false, listViewModel.loadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        DaggerCategoryViewModelComponent.builder()
            .categoryApiModule(CategoryApiModuleTest(apiService))
            .build()
            .inject(listViewModel)
    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

    }

}