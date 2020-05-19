package com.yaremko.topbooks.model

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(rank)
        parcel.writeString(lastWeekRank)
        parcel.writeString(weeksOnList)
        parcel.writeString(publisher)
        parcel.writeString(description)
        parcel.writeString(bookTitle)
        parcel.writeString(bookAuthor)
        parcel.writeString(bookContributor)
        parcel.writeString(bookImageURL)
        parcel.writeString(amazonLink)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Books> {
        override fun createFromParcel(parcel: Parcel): Books {
            return Books(parcel)
        }

        override fun newArray(size: Int): Array<Books?> {
            return arrayOfNulls(size)
        }
    }
}