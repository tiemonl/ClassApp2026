package edu.nku.classapp.data.repository

import edu.nku.classapp.data.RickAndMortyApi
import edu.nku.classapp.data.model.RickAndMortyApiResponse
import javax.inject.Inject

class RickAndMortyRepositoryReal @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) : RickAndMortyRepository {
    override suspend fun getCharacters(): RickAndMortyApiResponse {
        val result = rickAndMortyApi.getCharacters()
        return if (result.isSuccessful) {
            RickAndMortyApiResponse.Success(result.body()?.characters ?: emptyList())
        } else {
            RickAndMortyApiResponse.Error
        }
    }
}