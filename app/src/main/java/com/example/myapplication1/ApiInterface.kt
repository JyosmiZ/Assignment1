package com.example.myapplication1

import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("orgs/fossasia/repos")
    fun getData() : Call<List<MyDataItem>>
}