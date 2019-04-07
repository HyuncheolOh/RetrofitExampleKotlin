package com.example.retrofitexamplekotlin.data.model

import com.example.retrofitexamplekotlin.data.model.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SOAnswerResponse {

    @SerializedName("items")
    @Expose
    var items : List<Item>? = null

    @SerializedName("has_more")
    @Expose
    var hasMore: Boolean? = null

    @SerializedName("backoff")
    @Expose
    var backoff : Int? = null

    @SerializedName("quota_max")
    @Expose
    var quotaMax : Int? = null

    @SerializedName("quota_remaining")
    @Expose
    var quotaRemaining : Int? = null



}