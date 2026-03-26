package edu.nku.classapp.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.nku.classapp.data.RickAndMortyCharacter
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CharacterListViewModel @Inject constructor() : ViewModel() {
    private val names = listOf("Rick", "Morty", "Summer", "Jerry", "Beth")
    private val lastNames = listOf("Sanchez", "Sand", "Smith")
    private val planets = listOf("Earth", "Saturn", "Venus", "Mars", "Pluto")
    private val characters = mutableListOf<RickAndMortyCharacter>()

    init {
        createCharacters()
    }

    fun fillData() = characters.toList()

    fun fetchById(id: Int) = characters.first { it.id == id }

    private fun createCharacters() = (0..40).map {
        characters.add(
            RickAndMortyCharacter(
                id = it,
                name = "${names.random()} ${lastNames.random()}",
                age = Random.nextInt(1, 100),
                planet = planets.random(),
                height = Random.nextInt(1, 100),
                weight = Random.nextInt(1, 100),
                image = "https://rickandmortyapi.com/api/character/avatar/${
                    Random.nextInt(
                        1,
                        200
                    )
                }.jpeg",
            )
        )
    }
}