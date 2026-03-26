package edu.nku.classapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import edu.nku.classapp.databinding.FragmentCharacterDetailBinding
import edu.nku.classapp.viewmodels.CharacterListViewModel

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: CharacterListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)

        if (arguments != null) {
            val character = viewModel.fetchById(requireArguments().getInt(BUNDLE_ID))

            binding.characterName.text = character.name

        }


        return binding.root
    }

    companion object {
        private const val BUNDLE_ID = "character_id"

        fun newInstance(id: Int) = CharacterDetailFragment().apply {
            arguments = bundleOf(BUNDLE_ID to id)
        }
    }
}