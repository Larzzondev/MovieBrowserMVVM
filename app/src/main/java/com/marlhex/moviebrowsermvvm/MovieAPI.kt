package com.marlhex.moviebrowsermvvm

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "bf718d4dd8b23985d9c3edbcfd440a27",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<ApiResponse>
}

class MovieApi {
    private val apiService: MovieApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(MovieApiService::class.java)
    }

    suspend fun getMovies(page: Int): Result<ApiResponse> {
        return try {
            val response = apiService.getPopularMovies(page = page)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}