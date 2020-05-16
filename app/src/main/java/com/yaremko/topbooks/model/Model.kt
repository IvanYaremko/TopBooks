package com.yaremko.topbooks.model

import com.google.gson.annotations.SerializedName

data class Results (
//    @SerializedName("results")
//    val results: Results?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("list_name_encoded")
    val encodedName: String?,
    @SerializedName("updated")
    val updated: String?
)