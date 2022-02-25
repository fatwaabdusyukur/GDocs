package com.made.gdocs.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.made.core.data.Resource
import com.made.core.domain.model.Game
import com.made.gdocs.R
import com.made.gdocs.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel : DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val id = intent.getIntExtra(GAME_ID, 0)
        detailViewModel.getGameById(id).observe(this) { game ->
            if (game != null) {
                when(game) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        showLoading(false)
                        showGame(game.data)
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, "${game.message}", Toast.LENGTH_SHORT).show()
                        showLoading(false)
                    }
                }
            }
        }

    }

    private fun showLoading(isLoading : Boolean) {
        binding.loading.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.gameContainer.visibility = if (!isLoading) View.VISIBLE else View.GONE
    }

    private fun showGame(game: Game?) {

        if (game != null) {

            val format = SimpleDateFormat("yyy-M-dd", Locale.getDefault()).parse(game.released)
            val newDate = SimpleDateFormat("yyy-MMM-dd", Locale.getDefault()).format(format!!).toString().split("-").toList()

            with(binding) {
                Glide.with(this@DetailActivity)
                    .load(game.bgImage)
                    .into(gameImg)

                gameMetascore.text = game.metascore.toString()
                gameTitle.text = game.name
                gameSynopsis.text = game.description

                gameYear.text = newDate[0]
                gameMonth.text = newDate[1]
                gameDate.text = newDate[2]

                gamePlatforms.text = game.platforms
                gameGenre.text = game.genres
                gamePublisher.text = game.publisher
                gameDeveloper.text = game.developers

                val state: Boolean
                val msg : String

                if (game.isFavorite) {
                    btnFavorite.text = resources.getString(R.string.favorited)
                    state = false
                    msg = resources.getString(R.string.task_delete_success)
                } else {
                    btnFavorite.text = resources.getString(R.string.favorite)
                    state = true
                    msg = resources.getString(R.string.task_add_success)
                }


                btnFavorite.setOnClickListener {
                    detailViewModel.setFavoriteGame(game, state)
                    Snackbar.make(binding.detailContainer, msg, Snackbar.LENGTH_LONG).show()
                }
            }
        }

    }

    companion object {
        const val GAME_ID = "game_id"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}