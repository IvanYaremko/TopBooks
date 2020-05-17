package com.yaremko.topbooks.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListName(
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("copyright")
    @Expose
    val copyright: String?,
    @SerializedName("num_results")
    @Expose
    val numResults: Int?,
    @SerializedName("last_modified")
    @Expose
    val lastModified: String?,
    @SerializedName("results")
    @Expose
    val searchResult: SearchResults?

)

data class SearchResults(
    @SerializedName("list_name")
    @Expose
    val listName: String?,
    @SerializedName("list_name_encoded")
    @Expose
    val listNameEncoded: String?,
    @SerializedName("bestsellers_date")
    @Expose
    val bestSellersData: String?,
    @SerializedName("published_date")
    @Expose
    val publishedDate: String?,
    @SerializedName("normal_list_ends_at")
    @Expose
    val entries: Int?,
    @SerializedName("updated")
    @Expose
    val updated: String?,
    @SerializedName("books")
    @Expose
    val bookList: ArrayList<Books>?
)

data class Books (
    @SerializedName("rank")
    @Expose
    val rank: String?,
    @SerializedName("rank_last_week")
    @Expose
    val lastWeekRank: String?,
    @SerializedName("weeks_on_list")
    @Expose
    val weeksOnList: String?,
    @SerializedName("publisher")
    @Expose
    val publisher: String?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("title")
    @Expose
    val bookTitle: String?,
    @SerializedName("author")
    @Expose
    val bookAuthor: String?,
    @SerializedName("contributor")
    @Expose
    val bookContributor: String?,
    @SerializedName("book_image")
    @Expose
    val bookImageURL: String?,
    @SerializedName("amazon_product_url")
    @Expose
    val amazonLink: String?
)