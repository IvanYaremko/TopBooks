package com.yaremko.topbooks.view.books

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
import com.yaremko.topbooks.model.ListName
import com.yaremko.topbooks.model.Result
import com.yaremko.topbooks.viewmodel.BookListViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookListFragment : Fragment() {

    private lateinit var categoryList: Result
    private lateinit var viewModel: BookListViewModel
    private  var bookListAdapter = BookListAdapter(arrayListOf())

    private val listNameDataObserver = Observer<ListName> {
        listName -> listName?.let {
            it.searchResult?.let {
                    it.bookList?.let {
                        arrayList -> bookListAdapter.updateBookList(arrayList)
                        booksRV.visibility = View.VISIBLE
                    }
            }
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> {
        isLoading -> progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
        if(isLoading) {
            errorText.visibility = View.GONE
            booksRV.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean> {
        errorText.visibility = if (it) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            categoryList = BookListFragmentArgs.fromBundle(it).categoryResult
        }

        (activity as AppCompatActivity).supportActionBar?.title = categoryList.displayName

        viewModel = ViewModelProviders.of(this).get(BookListViewModel::class.java)
        viewModel.listName.observe(this, listNameDataObserver)
        viewModel.loading.observe(this, loadingLiveDataObserver)
        viewModel.loadError.observe(this, errorLiveDataObserver)

        // Checks for nullability before passing the argument to the function
        categoryList.encodedName?.let { viewModel.getBookList(it) }

        booksRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookListAdapter
        }

    }

}
