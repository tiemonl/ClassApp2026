package edu.nku.classapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.nku.classapp.data.RickAndMortyCharacter
import edu.nku.classapp.databinding.CharacterCardViewBinding

class RickAndMortyCharacterAdapter(
    private val characters: List<RickAndMortyCharacter>,
    private val onCharacterClicked: (position: Int) -> Unit,
) : RecyclerView.Adapter<RickAndMortyCharacterAdapter.RickAndMortyCharacterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyCharacterViewHolder {
        val binding =
            CharacterCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RickAndMortyCharacterViewHolder(binding) { position ->
            onCharacterClicked(position)
        }
    }

    override fun onBindViewHolder(
        holder: RickAndMortyCharacterViewHolder,
        position: Int
    ) = holder.bind(characters[position])

    override fun getItemCount() = characters.size

    class RickAndMortyCharacterViewHolder(
        private val binding: CharacterCardViewBinding,
        private val onCharacterClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onCharacterClicked(adapterPosition)
            }
        }

        fun bind(character: RickAndMortyCharacter) {
            binding.characterName.text = character.name
            binding.characterPlanet.text = character.planet
            Glide.with(binding.root).load(character.image).into(binding.characterImage)
        }
    }
}