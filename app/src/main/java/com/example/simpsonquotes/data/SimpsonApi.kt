package com.example.simpsonquotes.data

import retrofit2.Response
import retrofit2.http.GET

interface SimpsonApi {

    @GET("/quotes")
    suspend fun getQuote(): Response<List<SimpsonsResponse>>
}