package com.yaremko.topbooks.view.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yaremko.topbooks.R
import com.yaremko.topbooks.model.Names
import com.yaremko.topbooks.viewmodel.CategoryListViewModel
import kotlinx.android.synthetic.main.fragment_category_list.*


class CategoryListFragment : Fragment() {

    private lateinit var viewModel: CategoryListViewModel
    private val categoryAdapter = CategoryListAdapter(arrayListOf())

    private val categoryListDataObserver = Observer<Names> {
        list -> list?.let {
            it.results?.let {
                    arrayList -> categoryAdapter.updateCategoryList(arrayList)
            }
        }
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

        viewModel = ViewModelProviders.of(this).get(CategoryListViewModel::class.java)
        viewModel.categories.observe(this, categoryListDataObserver)
        viewModel.getCategories()

        categoryRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
    }

}
