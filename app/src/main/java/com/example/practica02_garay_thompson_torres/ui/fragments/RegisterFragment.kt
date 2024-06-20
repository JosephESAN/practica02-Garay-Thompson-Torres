package com.example.practica02_garay_thompson_torres.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.practica02_garay_thompson_torres.R
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var countrySpinner: Spinner
    private lateinit var teamName: EditText
    private lateinit var playerName: EditText
    private lateinit var position: EditText
    private lateinit var dorsal: EditText
    private lateinit var imageUrl: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        db = FirebaseFirestore.getInstance()
        countrySpinner = view.findViewById(R.id.country_spinner)
        playerName = view.findViewById(R.id.player_name)
        position = view.findViewById(R.id.position)
        dorsal = view.findViewById(R.id.dorsal)
        imageUrl = view.findViewById(R.id.image_url)
        saveButton = view.findViewById(R.id.save_button)

        saveButton.setOnClickListener {
            savePlayer()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCountries()
    }

    private fun loadCountries() {
        if (isAdded) { // Ensure fragment is attached to a context
            db.collection("paises").get().addOnSuccessListener { result ->
                val countries = result.map { it.getString("nombre") ?: "" }
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                countrySpinner.adapter = adapter
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Error loading countries", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun savePlayer() {
        if (isAdded) { // Ensure fragment is attached to a context
            val player = hashMapOf(
                "country" to countrySpinner.selectedItem.toString(),
                "playerName" to playerName.text.toString(),
                "position" to position.text.toString(),
                "dorsal" to dorsal.text.toString(),
                "imageUrl" to imageUrl.text.toString()
            )

            db.collection("jugadores").add(player).addOnSuccessListener {
                Toast.makeText(requireContext(), "Player saved", Toast.LENGTH_SHORT).show()
                clearFields()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Error saving player", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        playerName.text.clear()
        position.text.clear()
        dorsal.text.clear()
        imageUrl.text.clear()
        countrySpinner.setSelection(0)
    }
}
