package com.example.gamebacklog.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.game_item.view.*
import java.time.format.DateTimeFormatter


class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(game: Game){
            itemView.tvGameName.text = game.title
            itemView.tvPlatform.text = game.platforms
            itemView.tvReleaseDate.text = "Release: " + game.releaseDate.format(DateTimeFormatter.ofPattern("d MMMM y"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = games.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(games[position])
    }
}