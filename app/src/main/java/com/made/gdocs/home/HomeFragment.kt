package com.made.gdocs.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.made.core.data.Resource
import org.koin.android.viewmodel.ext.android.viewModel
import com.made.gdocs.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel : HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameAdapter = GameAdapter {
            Snackbar.make(
                binding.containerHome,
                it.name,
                Snackbar.LENGTH_SHORT
            ).show()
        }

        homeViewModel.getAllGame().observe(viewLifecycleOwner,  { game ->
            if (game != null) {
                when(game) {
                    is Resource.Loading -> binding.loading.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.loading.visibility = View.GONE
                        gameAdapter.setData(game.data!!)
                    }
                    is Resource.Error -> binding.loading.visibility = View.GONE
                }
            }
        })

        with(binding.rvGame) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = gameAdapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}