package com.yaremko.topbooks.view.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yaremko.topbooks.R
import com.yaremko.topbooks.model.Names
import com.yaremko.topbooks.viewmodel.CategoryListViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_category_list.*

/*
    This Fragment is used to display the categories of books
    The categories are retrieved from nytimes books api
 */
class CategoryListFragment : Fragment() {

    private lateinit var viewModel: CategoryListViewModel
    private val categoryAdapter = CategoryListAdapter(arrayListOf())

     /*
         Observer that listens for any changes and updates the adapter
         Checks for nullability and sends the Result class member variable from the Names Class
         To the adapter to update the recycler view
      */
    private val categoryListDataObserver = Observer<Names> {
        list -> list?.let {
            it.results?.let {
                    arrayList -> categoryAdapter.updateCategoryList(arrayList)
                    categoryRV.visibility = View.VISIBLE
            }
        }
    }

    /*
        Observer that toggles the progress bar widget on or off
     */
    private val loadingLiveDataObserver = Observer<Boolean> {
        isLoading -> loadingBar.visibility = if(isLoading) View.VISIBLE else View.GONE
        if(isLoading)
        {
            loadError.visibility = View.GONE
            categoryRV.visibility = View.GONE
        }
    }

    private val loadErrorLiveDataObserver = Observer<Boolean> {
        isError -> loadError.visibility = if(isError) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Categories"

        viewModel = ViewModelProviders.of(this).get(CategoryListViewModel::class.java)
        viewModel.categories.observe(this, categoryListDataObserver)
        viewModel.loading.observe(this, loadingLiveDataObserver)
        viewModel.loadError.observe(this, loadErrorLiveDataObserver)
        viewModel.getCategories()

        categoryRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
    }

}
