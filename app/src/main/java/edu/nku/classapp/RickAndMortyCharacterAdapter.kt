package edu.nku.classapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RickAndMortyCharacterAdapter(
    private val characters: List<RickAndMortyCharacter>
) : RecyclerView.Adapter<RickAndMortyCharacterAdapter.RickAndMortyCharacterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyCharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.character_card_view,
            parent,
            false
        )

        return RickAndMortyCharacterViewHolder(view) { position ->
            val character = characters[position]


            val bundle = bundleOf(
                "name" to character.name,
                "age" to character.age
            )


            val detailFragment = CharacterDetailFragment()

            detailFragment.arguments = bundle

            val activity = view.context as AppCompatActivity

            activity.supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    detailFragment
                )
                addToBackStack(null)
            }

        }
    }

    override fun onBindViewHolder(
        holder: RickAndMortyCharacterViewHolder,
        position: Int
    ) {
        val character = characters[position]
        holder.characterName.text = character.name
        holder.characterPlanet.text = character.planet
        holder.characterImage.setImageResource(R.drawable.ic_launcher_background)
        Glide.with(holder.itemView.context)
            .load(character.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.characterImage)

    }

    override fun getItemCount() = characters.size

    class RickAndMortyCharacterViewHolder(
        itemView: View,
        private val onCharacterClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onCharacterClicked(adapterPosition)
            }
        }

        val characterImage: ImageView = itemView.findViewById(R.id.character_image)
        val characterName: TextView = itemView.findViewById(R.id.character_name)
        val characterPlanet: TextView = itemView.findViewById(R.id.character_planet)
    }


}