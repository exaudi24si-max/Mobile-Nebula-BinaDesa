package com.example.exaudi_nebula.data.api

import com.example.exaudi_nebula.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("v2/list")
    suspend fun getPhotos(): List<PhotoModel>
}