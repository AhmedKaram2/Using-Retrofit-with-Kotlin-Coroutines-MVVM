package com.populararticles.data.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Medium : Serializable {
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("subtype")
    @Expose
    var subtype: String? = null

    @SerializedName("caption")
    @Expose
    var caption: String? = null

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    @SerializedName("approved_for_syndication")
    @Expose
    var approvedForSyndication:Long = 0

    @SerializedName("media-metadata")
    @Expose
    var mediaMetadata: List<MediaMetadata>? = null
}