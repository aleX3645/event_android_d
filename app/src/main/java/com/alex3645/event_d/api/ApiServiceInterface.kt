package com.alex3645.event_d.api

import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.util.Constants
import io.reactivex.rxjava3.core.Observable
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiServiceInterface {
    @GET("api/usr/searchConferences/g")
    fun getConferenceByText(@Header("Authorization") token: String): Observable<List<Conference>>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.SPRING_TICKET_LAN_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}