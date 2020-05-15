package com.julius.simplifiedcodingcrud.api

import com.julius.simplifiedcodingcrud.model.DefaultResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("createuser")
//    we have a call createuser and the type of the call is default response
    fun createUser(
//        values being passed
        @Field("email") email:String,
        @Field("name") name:String,
        @Field("password") password:String,
        @Field("school") school:String
    ):Call<DefaultResponse>
}