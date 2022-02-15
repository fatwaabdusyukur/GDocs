package com.made.gdocs.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.made.core.data.Resource
import org.koin.android.viewmodel.ext.android.viewModel
import com.made.gdocs.databinding.FragmentHomeBinding
import com.made.gdocs.detail.DetailActivity

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
            val i = Intent(context, DetailActivity::class.java)
            i.putExtra(DetailActivity.GAME_ID, it.id)
            startActivity(i)
        }

        homeViewModel.getAllGame().observe(viewLifecycleOwner,  { game ->
            if (game != null) {
                when(game) {
                    is Resource.Loading -> binding.loading.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.loading.visibility = View.GONE
                        gameAdapter.setData(game.data!!)
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, "${game.message}", Toast.LENGTH_SHORT).show()
                        binding.loading.visibility = View.GONE
                    }
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