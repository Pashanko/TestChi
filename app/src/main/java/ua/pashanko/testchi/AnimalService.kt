package ua.pashanko.testchi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AnimalService {
    @GET("shibes?count=10&urls=true&httpsUrls=true")
    fun getAnimalImages(): Call<List<String>>

    companion object {
        private const val BASE_URL = "https://shibe.online/api/"
        fun create(): AnimalService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(AnimalService::class.java)
        }
    }
}
