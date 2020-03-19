package com.example.steenpapierschaar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.scores.view.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.scores, parent, false)
        )
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(game: Game){
            itemView.tvWinner.text = game.winner
            itemView.tvDate.text = game.date

            when(game.computerMove){
                1 -> itemView.ivComputerTurn.setImageResource(R.drawable.paper)
                2 -> itemView.ivComputerTurn.setImageResource(R.drawable.rock)
                3 -> itemView.ivComputerTurn.setImageResource(R.drawable.scissors)
            }
            when(game.playerMove){
                1 -> itemView.ivPlayerTurn.setImageResource(R.drawable.paper)
                2 -> itemView.ivPlayerTurn.setImageResource(R.drawable.rock)
                3 -> itemView.ivPlayerTurn.setImageResource(R.drawable.scissors)
            }
        }
    }
}