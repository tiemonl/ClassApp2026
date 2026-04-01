package edu.nku.classapp.data.repository

import edu.nku.classapp.data.model.RickAndMortyApiResponse

interface RickAndMortyRepository {
    suspend fun getCharacters(): RickAndMortyApiResponse
}