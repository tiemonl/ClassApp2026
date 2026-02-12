package edu.nku.classapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RickAndMortyCharacterAdapter(
    private val characters: List<RickAndMortyCharacter>
) : RecyclerView.Adapter<RickAndMortyCharacterAdapter.RickAndMortyCharacterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyCharacterViewHolder {
        return RickAndMortyCharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.character_card_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: RickAndMortyCharacterViewHolder,
        position: Int
    ) {
        val character = characters[position]
        holder.characterName.text = character.name
        holder.characterPlanet.text = character.planet
        holder.characterImage.setImageResource(R.drawable.ic_launcher_background)
    }

    override fun getItemCount() = characters.size

    class RickAndMortyCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterImage: ImageView = itemView.findViewById(R.id.character_image)
        val characterName: TextView = itemView.findViewById(R.id.character_name)
        val characterPlanet: TextView = itemView.findViewById(R.id.character_planet)
    }


}