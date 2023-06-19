package pe.com.coroutines_kotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val URL = "https://superheroapi.com/"

    private val retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();

    fun getInstance():ApiService{
        return retrofit.create(ApiService::class.java)
    }

}