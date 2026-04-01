package edu.nku.classapp.data

import edu.nku.classapp.data.model.RickAndMortyCharactersDTO
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(): Response<RickAndMortyCharactersDTO>

}