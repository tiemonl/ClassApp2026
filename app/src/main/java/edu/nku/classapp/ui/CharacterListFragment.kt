package edu.nku.classapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import edu.nku.classapp.R
import edu.nku.classapp.databinding.FragmentCharacterListBinding
import edu.nku.classapp.ui.adapters.RickAndMortyCharacterAdapter
import edu.nku.classapp.viewmodels.CharacterListViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding
        get() = _binding!!

    val viewModel: CharacterListViewModel by activityViewModels()
    val charactersAdapter = RickAndMortyCharacterAdapter { character ->
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                CharacterDetailFragment.newInstance(character.id)
            )
            addToBackStack(null)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        setUpObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = charactersAdapter
        }
        viewModel.fillData()
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            viewModel.characters.collect { event ->
                when (event) {
                    CharacterListViewModel.RickAndMortyCharacterState.Failure -> {
                        binding.errorMessage.isVisible = true
                        binding.progressBar.isVisible = false
                        binding.recyclerView.isVisible = false
                    }

                    CharacterListViewModel.RickAndMortyCharacterState.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.recyclerView.isVisible = false
                        binding.errorMessage.isVisible = false
                    }

                    is CharacterListViewModel.RickAndMortyCharacterState.Success -> {
                        charactersAdapter.refreshData(event.characters)
                        binding.recyclerView.isVisible = true
                        binding.progressBar.isVisible = false
                        binding.errorMessage.isVisible = false
                    }
                }
            }
        }
    }

}