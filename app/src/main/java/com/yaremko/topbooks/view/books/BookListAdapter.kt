package com.yaremko.topbooks.view.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaremko.topbooks.R
import com.yaremko.topbooks.model.Books
import com.yaremko.topbooks.util.getProgressDrawable
import com.yaremko.topbooks.util.loadImage
import kotlinx.android.synthetic.main.book_item.view.*
import java.util.zip.Inflater

class BookListAdapter(private var bookList: ArrayList<Books>)
    : RecyclerView.Adapter<BookListAdapter.booksViewHolder>(){

    class booksViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateBookList(newBookList: ArrayList<Books>) {
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): booksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_item, parent, false)
        return booksViewHolder(view)
    }

    override fun getItemCount() = bookList.size

    override fun onBindViewHolder(holder: booksViewHolder, position: Int) {
        holder.view.rankText.text = bookList[position].rank
        holder.view.bookTitleText.text = bookList[position].bookTitle
        holder.view.bookAuthorText.text = bookList[position].bookContributor
        bookList[position].bookImageURL?.let { holder.view.bookImage.loadImage(it, getProgressDrawable(holder.view.context)) }

        // IMPLEMENT LISTENER
    }
}