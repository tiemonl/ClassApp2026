package edu.nku.classapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RickAndMortyCharactersDTO(
    val info: Info,
    @param:Json(name = "results")
    val characters: List<Character>
) {
    @JsonClass(generateAdapter = true)
    data class Info(
        val count: Int,
        val next: String,
        val pages: Int,
        val prev: Any?
    )

    @JsonClass(generateAdapter = true)
    data class Character(
        val created: String,
        val episode: List<String>,
        val gender: String,
        val id: Int,
        val image: String,
        val location: Location,
        val name: String,
        val origin: Origin,
        val species: String,
        val status: String,
        val type: String,
        val url: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Location(
            val name: String,
            val url: String
        )

        @JsonClass(generateAdapter = true)
        data class Origin(
            val name: String,
            val url: String
        )
    }
}