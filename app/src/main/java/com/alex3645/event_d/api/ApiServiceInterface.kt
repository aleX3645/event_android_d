package com.alex3645.event_d.api

import com.alex3645.event_d.model.*
import com.alex3645.event_d.util.Constants
import io.reactivex.rxjava3.core.Observable
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiServiceInterface {
    @GET("api/usr/searchConferences/{val}")
    fun getConferenceByText(@Path("val") text: String): Observable<List<Conference>>

    @GET("api/usr/searchEvents/{val}")
    fun getEventByText(@Path("val") text: String): Observable<List<Event>>

    @GET("api/usr/searchUsers/{val}")
    fun getUserByText(@Path("val") text: String): Observable<List<User>>


    @GET("api/usr/findConferencesByCategory/{categoryId}")
    fun getConferenceByCategory(@Path("categoryId") categoryId: Int): Observable<List<Conference>>

    @GET("api/usr/conference/{val}")
    fun getConferenceById(@Path("val") id: Int): Observable<Conference>

    @GET("api/usr/conferenceEvents/{val}")
    fun getEventByConferenceId(@Path("val") id: Int): Observable<List<Event>>

    @GET("api/usr/event/{val}")
    fun getEventById(@Path("val") id: Int): Observable<Event>

    @GET("api/usr/info/byId/{val}")
    fun getUserById(@Path("val") id: Int): Observable<User>


    @POST("api/login/user")
    fun authAsUser(@Body request: LoginRequestBody): Observable<AuthInfo>

    @POST("api/login/organizer")
    fun authAsOrganizer(@Body request: LoginRequestBody): Observable<AuthInfo>



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