package edu.nku.classapp.data.model

sealed interface RickAndMortyApiResponse {
    data class Success(val characters: List<RickAndMortyCharactersDTO.Character>) :
        RickAndMortyApiResponse

    data object Error : RickAndMortyApiResponse
}