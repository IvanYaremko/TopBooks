package com.yaremko.topbooks.model

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(listName)
        parcel.writeString(displayName)
        parcel.writeString(encodedName)
        parcel.writeString(oldestPublishedDate)
        parcel.writeString(newestPublishedDate)
        parcel.writeString(updated)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }

}
