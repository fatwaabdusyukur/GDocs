package com.made.gdocs.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.made.core.domain.model.Game
import com.made.gdocs.databinding.ItemsGameBinding

class GameAdapter(
    private val onClick : (Game) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var games = ArrayList<Game>()

    fun setData(newData : List<Game>) {
        games.clear()
        games.addAll(newData)
        notifyDataSetChanged()
    }

    inner class GameViewHolder(private val binding : ItemsGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            Glide.with(itemView.context)
                .load(game.bgImage)
                .into(binding.itemGameImage)

            binding.itemGameTitle.text = game.name
            binding.itemGameGenre.text = game.genres
            binding.itemGamePlatforms.text = game.platforms

            itemView.setOnClickListener { onClick(game) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder =
        GameViewHolder(ItemsGameBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val data = games[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = games.size
}