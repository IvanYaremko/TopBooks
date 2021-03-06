package com.yaremko.topbooks.view.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yaremko.topbooks.R
import com.yaremko.topbooks.model.Result
import kotlinx.android.synthetic.main.category_item.view.*

/*
    Adapter class to bind the list of categories into the recycler view
 */
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

        // Category listener that navigates to the next list of the clicked category
        holder.view.categoryItem.setOnClickListener {
            // elvis operator to check for nullability
            val category = categoryList[position]
            val action: NavDirections = CategoryListFragmentDirections.actionCategoryListFragmentToBookListFragment(category)
            Navigation.findNavController(holder.view).navigate(action)
        }
    }




}