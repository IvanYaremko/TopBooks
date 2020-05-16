package com.yaremko.topbooks.view.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaremko.topbooks.R
import com.yaremko.topbooks.model.Names
import com.yaremko.topbooks.model.Result
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryListAdapter(private var categoryList: ArrayList<Result>) :
    RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>(){

    class CategoryViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateCategoryList(newCategoryList: ArrayList<Result>) {
        categoryList.clear()
        categoryList.addAll(newCategoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.view.categoryText.text = categoryList[position].displayName
        holder.view.updatedText.text = categoryList[position].updated

        // Implement listener
    }


}