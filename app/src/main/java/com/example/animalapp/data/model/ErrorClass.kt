package com.example.animalapp.data.model

import com.google.gson.annotations.SerializedName

data class ErrorClass
    (
    @SerializedName("timestamp")
    var timestamp: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("error")
    var error: String,
    @SerializedName("path")
    var path: String
    )