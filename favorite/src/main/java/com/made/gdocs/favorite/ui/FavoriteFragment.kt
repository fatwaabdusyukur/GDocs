package com.made.gdocs.favorite.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.made.gdocs.detail.DetailActivity
import com.made.gdocs.favorite.databinding.FragmentFavoriteBinding
import com.made.gdocs.favorite.favModule
import com.made.gdocs.home.GameAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favViewModel : FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        loadKoinModules(favModule)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameAdapter = GameAdapter {
            val i = Intent(context, DetailActivity::class.java)
            i.putExtra(DetailActivity.GAME_ID, it.id)
            startActivity(i)
        }

        favViewModel.getFavoriteGames().observe(viewLifecycleOwner) { game ->
            if (game.isNotEmpty()) gameAdapter.setData(game)
            else {
                with(binding) {
                    rvFavGame.visibility = View.GONE
                    imgNotFound.visibility = View.VISIBLE
                    tvGameNotFound.visibility = View.VISIBLE
                }
            }
        }

        binding.rvFavGame.apply {
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