package com.example.firedatabase_assis

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firedatabase_assis.databinding.ActivityAnnonceAnalyticsBinding
import com.example.firedatabase_assis.databinding.ActivitySendFormBinding
import com.example.firedatabase_assis.databinding.ActivityWelcomeWindowBinding

class annonce_analytics : AppCompatActivity() {
    private lateinit var binding: ActivityAnnonceAnalyticsBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnnonceAnalyticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var selectedCity = intent.getStringExtra("city")

        val dbhelp = DB_class(applicationContext)
        val db = dbhelp.readableDatabase

        val query = "SELECT * FROM annonce WHERE city='" + selectedCity + "'"
        val cursor = db.rawQuery(query, null)

        val count = cursor.count-1 // This will give you the count of rows in the cursor
        cursor.close()
        binding.annonceParVille.text = "Il y a: $count Annonces dans la ville: $selectedCity"

    }
}