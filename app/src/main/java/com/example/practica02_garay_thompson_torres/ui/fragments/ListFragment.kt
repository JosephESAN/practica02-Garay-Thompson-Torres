package com.example.practica02_garay_thompson_torres.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica02_garay_thompson_torres.R
import com.example.practica02_garay_thompson_torres.adapter.PlayerAdapter
import com.example.practica02_garay_thompson_torres.model.Player
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Toast

class ListFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        db = FirebaseFirestore.getInstance()
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPlayers()
    }

    private fun loadPlayers() {
        if (isAdded) { // Ensure fragment is attached to a context
            db.collection("jugadores").get().addOnSuccessListener { result ->
                val players = result.map {
                    Player(
                        it.getString("country") ?: "",
                        it.getString("playerName") ?: "",
                        it.getString("position") ?: "",
                        it.getString("dorsal") ?: "",
                        it.getString("imageUrl") ?: ""
                    )
                }
                playerAdapter = PlayerAdapter(players)
                recyclerView.adapter = playerAdapter
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Error loading players", Toast.LENGTH_SHORT).show()
            }
        }
    }
}