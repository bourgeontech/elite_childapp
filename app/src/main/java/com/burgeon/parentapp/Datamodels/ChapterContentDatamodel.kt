package com.burgeon.parentapp.Datamodels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


/**
 * Created by Ajay K K on 29/06/20.
 */
class ChapterContentDatamodel {

    @SerializedName("status")
    @Expose
    val status: Boolean? = null
    @SerializedName("data")
    @Expose
    val data: List<contentDetails>? = null
    @SerializedName("msg")
    @Expose
    val msg: String? = null
}

class contentDetails {
    @SerializedName("id")
    @Expose
    val id: String? = null
    @SerializedName("chapter_id")
    @Expose
    val chapterId: String? = null
    @SerializedName("sort_order")
    @Expose
    val sortOrder: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("video")
    @Expose
    val video: String? = null
    @SerializedName("video_url")
    @Expose
    val videoUrl: String? = null
    @SerializedName("audio")
    @Expose
    val audio: String? = null
    @SerializedName("document")
    @Expose
    val document: String? = null
}