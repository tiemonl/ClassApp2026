package edu.nku.classapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class CharacterListFragment : Fragment() {

    private val names = listOf("Rick", "Morty", "Summer", "Jerry", "Beth")
    private val lastNames = listOf("Sanchez", "Sand", "Smith")
    private val planets = listOf("Earth", "Saturn", "Venus", "Mars", "Pluto")
    val characters = mutableListOf<RickAndMortyCharacter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(
            R.layout.fragment_character_list,
            container,
            false
        )

        for (i in 0..40) {
            characters.add(createCharacter(id = i))
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RickAndMortyCharacterAdapter(characters)

        return view
    }

    private fun createCharacter(id: Int) = RickAndMortyCharacter(
        id = id,
        name = "${names.random()} ${lastNames.random()}",
        age = Random.nextInt(1, 100),
        planet = planets.random(),
        height = Random.nextInt(1, 100),
        weight = Random.nextInt(1, 100),
        image = "https://rickandmortyapi.com/api/character/avatar/${Random.nextInt(1, 200)}.jpeg",
    )

}