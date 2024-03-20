package com.example.firedatabase_assis

import android.R
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firedatabase_assis.databinding.ActivitySendFormBinding

class SendForm : AppCompatActivity() {
    private lateinit var binding: ActivitySendFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sample list of cities
        val cities = listOf("Casa", "Agadir", "Rabat", "Settat")
        val secteurList = listOf("Software Engineering", "AI", "Data Analysis", "Trading")

        // Create an ArrayAdapter using the string array and a default spinner layout
        val citiesAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, cities)
        val secteurAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, secteurList)

        // Specify the layout to use when the list of choices appears
        citiesAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        secteurAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        binding.citySpinner.adapter = citiesAdapter
        binding.secteurSpinner.adapter = secteurAdapter

        var dbhelp = DB_class(applicationContext)
        var db = dbhelp.writableDatabase
        binding.btnrgs.setOnClickListener {

            var titre = binding.titre.text.toString()
            var selectedSecteur = binding.secteurSpinner.selectedItem.toString()
            var selectedCity = binding.citySpinner.selectedItem.toString()
            var typeContrat = binding.typeContrat.text.toString()
            var desc=binding.description.text.toString()



            if (titre.isNotEmpty() && desc.isNotEmpty() && selectedSecteur.isNotEmpty()) {
                var data = ContentValues()

                data.put("titre", titre)
                data.put("secteur", selectedSecteur)
                data.put("city", selectedCity)
                data.put("type_contrat", typeContrat)
                data.put("desc",desc)

                var rs: Long = db.insert("annonce", null, data)

                if (!rs.equals(-1)) {

                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Annonce ajoutée avec succes ")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.titre.text.clear()
                    binding.description.text.clear()
                    binding.typeContrat.text.clear()

                    //rs.close()

                    startActivity(
                        Intent(this,annonce_analytics::class.java).putExtra("city",selectedCity)
                    )

                } else {
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("l'ajout de l'annonce est echoué ")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.titre.text.clear()
                    binding.description.text.clear()
                    binding.typeContrat.text.clear()
                }
            } else {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show()
            }
        }

    }


}