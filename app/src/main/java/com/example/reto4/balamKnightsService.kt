package com.example.reto4

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface balamKnightsService {
    @POST("accesos_dev/api/Auth/")
    fun getResultLogin(@Body parametersDto: ParametersDto): Call<balamKnightsResponse>
}

data class ParametersDto(val User:String, val Password:String)