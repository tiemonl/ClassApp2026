package edu.nku.classapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.nku.classapp.data.model.RickAndMortyCharactersDTO
import edu.nku.classapp.databinding.CharacterCardViewBinding

class RickAndMortyCharacterAdapter(
    private val onCharacterClicked: (character: RickAndMortyCharactersDTO.Character) -> Unit,
) : RecyclerView.Adapter<RickAndMortyCharacterAdapter.RickAndMortyCharacterViewHolder>() {

    private val rickAndMortyCharacters = mutableListOf<RickAndMortyCharactersDTO.Character>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyCharacterViewHolder {
        val binding =
            CharacterCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RickAndMortyCharacterViewHolder(binding) { position ->
            onCharacterClicked(rickAndMortyCharacters[position])
        }
    }

    override fun onBindViewHolder(
        holder: RickAndMortyCharacterViewHolder,
        position: Int
    ) = holder.bind(rickAndMortyCharacters[position])

    override fun getItemCount() = rickAndMortyCharacters.size

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(characters: List<RickAndMortyCharactersDTO.Character>) {
        rickAndMortyCharacters.clear()
        rickAndMortyCharacters.addAll(characters)
        notifyDataSetChanged()
    }

    class RickAndMortyCharacterViewHolder(
        private val binding: CharacterCardViewBinding,
        private val onCharacterClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onCharacterClicked(adapterPosition)
            }
        }

        fun bind(character: RickAndMortyCharactersDTO.Character) {
            binding.characterName.text = character.name
            binding.characterPlanet.text = character.location.name
            Glide.with(binding.root).load(character.image).into(binding.characterImage)
        }
    }
}