package com.ziadoua.zcalc

import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("calculation") val calculation: String,
    @SerializedName("result") val result: String,
    @SerializedName("time") val time: String
)
