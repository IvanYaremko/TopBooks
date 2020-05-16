package com.yaremko.topbooks.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Names (
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("copyright")
    @Expose
    val copyright: String?,
    @SerializedName("num_results")
    @Expose
    val numResults: Int?,
    @SerializedName("results")
    @Expose
    val results: ArrayList<Result>?

)

data class Result(
    @SerializedName("list_name")
    @Expose
    val listName: String?,
    @SerializedName("display_name")
    @Expose
    val displayName: String?,
    @SerializedName("list_name_encoded")
    @Expose
    val encodedName: String?,
    @SerializedName("oldest_published_date")
    @Expose
    val oldestPublishedDate: String?,
    @SerializedName("newest_published_date")
    @Expose
    val newestPublishedDate: String?,
    @SerializedName("updated")
    @Expose
    val updated: String?
)
