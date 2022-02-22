package com.made.gdocs.platform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.made.gdocs.R
import com.made.gdocs.databinding.ActivityDetailPlatformBinding
import com.made.gdocs.detail.DetailActivity
import com.made.gdocs.home.GameAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class DetailPlatformActivity : AppCompatActivity() {

    private var _binding : ActivityDetailPlatformBinding? = null
    private val binding get() = _binding!!

    private val platformViewModel : PlatformViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailPlatformBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val keyword = intent.getStringExtra(PLATFORM_NAME)

        binding.titlePage.text = resources.getString(R.string.detail_platform_title_page, keyword)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val gameAdapter = GameAdapter {
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra(DetailActivity.GAME_ID, it.id)
            startActivity(i)
        }

        platformViewModel.getGamesByPlatform(keyword!!).observe(this) { game ->
            if (game.isNotEmpty()) gameAdapter.setData(game)
            else binding.imgNotFound.visibility = View.VISIBLE
        }

        binding.rvPlatformGames.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DetailPlatformActivity)
            adapter = gameAdapter
        }

    }

    companion object {
        const val PLATFORM_NAME = "platform_name"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}