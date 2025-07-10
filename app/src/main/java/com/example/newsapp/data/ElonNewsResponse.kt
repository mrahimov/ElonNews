package com.example.newsapp.data

import com.google.gson.annotations.SerializedName

data class ElonNewsResponse(
    @SerializedName("source")
    val source: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlImage")
    val urlImage: String,
    @SerializedName("publishDate")
    var publishDate: String,
)

val mockNews = ElonNewsResponse(
    source = "Gizmodo.com",
    title = "Elon Says He’s Launching ‘the America Party’ to Compete With Democrats and Republicans If Budget Bill Passes",
    description = "Our country needs an alternative to the Democrat-Republican uniparty, Musk tweeted.",
    url = "https://gizmodo.com/elon-says-hes-launching-the-america-party-to-compete-with-democrats-and-republicans-if-budget-bill-passes-2000622406",
    urlImage = "https://gizmodo.com/app/uploads/2025/06/elon-musk-trump-1200x675.jpg",
    publishDate = "2025-06-30T23:36:18Z"
)