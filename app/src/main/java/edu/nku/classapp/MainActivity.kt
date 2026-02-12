package edu.nku.classapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    val characters = mutableListOf<RickAndMortyCharacter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..40) {
            characters.add(createCharacter(id = i))
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RickAndMortyCharacterAdapter(characters)
    }

    private val names = listOf("Rick", "Morty", "Summer", "Jerry", "Beth")
    private val lastNames = listOf("Sanchez", "Sand", "Smith")
    private val planets = listOf("Earth", "Saturn", "Venus", "Mars", "Pluto")

    private fun createCharacter(id: Int) = RickAndMortyCharacter(
        id = id,
        name = "${names.random()} ${lastNames.random()}",
        age = Random.nextInt(1, 100),
        planet = planets.random(),
        height = Random.nextInt(1, 100),
        weight = Random.nextInt(1, 100)
    )
}