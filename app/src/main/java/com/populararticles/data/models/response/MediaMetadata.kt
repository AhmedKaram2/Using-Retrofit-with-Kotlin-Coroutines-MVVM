package com.populararticles.data.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MediaMetadata : Serializable {
    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("format")
    @Expose
    var format: String? = null

    @SerializedName("height")
    @Expose
    var height: Long? = null

    @SerializedName("width")
    @Expose
    var width:Long = 0
}