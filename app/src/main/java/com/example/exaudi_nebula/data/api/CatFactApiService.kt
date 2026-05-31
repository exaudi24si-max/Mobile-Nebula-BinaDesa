package com.example.exaudi_nebula.data.api

import com.example.exaudi_nebula.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}