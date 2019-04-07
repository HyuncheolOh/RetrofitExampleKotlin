package com.example.retrofitexamplekotlin.data.remote

class ApiUtils {
    companion object {
        val BASE_URL : String = "https://api.stackexchange.com/2.2/"

        fun getSOService() : SOService {
            return RetrofitClient.getClient(BASE_URL)
                .create(SOService::class.java)
        }
    }
}