package com.yaremko.topbooks.view.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.yaremko.topbooks.R
import com.yaremko.topbooks.model.Result
import com.yaremko.topbooks.viewmodel.BookListViewModel

class BookListFragment : Fragment() {

    private lateinit var categoryList: Result
    private lateinit var bookListViewModel: BookListViewModel

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



    }

}
