package com.yaremko.topbooks.view.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.yaremko.topbooks.R
import com.yaremko.topbooks.model.Books
import com.yaremko.topbooks.util.getProgressDrawable
import com.yaremko.topbooks.util.loadImage
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment() {

    private lateinit var book: Books

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = ""

        arguments?.let {
            book = BookDetailFragmentArgs.fromBundle(it).book
        }

        book.bookImageURL?.let {
            context?.let {
                    context -> getProgressDrawable(context) }?.let {
                    progressDrawable -> detailImage.loadImage(it, progressDrawable)
            }
        }

        book.bookAuthor?.let {
            detailTitle.text = it
        }

    }

}
