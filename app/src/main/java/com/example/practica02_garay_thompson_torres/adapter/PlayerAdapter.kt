package com.example.practica02_garay_thompson_torres.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practica02_garay_thompson_torres.R
import com.example.practica02_garay_thompson_torres.model.Player
import com.squareup.picasso.Picasso

class PlayerAdapter(private val players: List<Player>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerName: TextView = view.findViewById(R.id.player_name)
        val position: TextView = view.findViewById(R.id.position)
        val dorsal: TextView = view.findViewById(R.id.dorsal)
        val imageUrl: ImageView = view.findViewById(R.id.image_url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.playerName.text = player.playerName
        holder.position.text = player.position
        holder.dorsal.text = player.dorsal
    }

    override fun getItemCount() = players.size
}
