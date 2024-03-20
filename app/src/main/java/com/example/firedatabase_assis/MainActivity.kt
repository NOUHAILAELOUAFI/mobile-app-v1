package com.example.firedatabase_assis

import android.R
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.firedatabase_assis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sample list of cities
        val cities = listOf("Casa", "Agadir", "Rabat", "Settat")

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        binding.citySpinner.adapter = adapter

        var dbhelp = DB_class(applicationContext)
        var db = dbhelp.writableDatabase
        binding.btnrgs.setOnClickListener {

            var email = binding.ed1.text.toString()

            //email validation
            val isValidEmail = isValidEmail(email)
            if (!isValidEmail) {
                Toast.makeText(this, "Veuillez entrer une adresse email valide", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //var username = binding.ed2.text.toString()
            var password = binding.ed2.text.toString()
            var password2=binding.ed3.text.toString()
            if (password!=password2) {
                Toast.makeText(this, "Veuillez Saisir le meme mot de passe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Retrieve the selected city from the Spinner
            var selectedCity = binding.citySpinner.selectedItem.toString()

            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
            val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
            val selectedRadioButtonText = selectedRadioButton.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) {
                var data = ContentValues()
                data.put("email", binding.ed1.text.toString())
                //data.put("username", binding.ed2.text.toString())
                data.put("pswd", binding.ed2.text.toString())
                data.put("city", selectedCity)
                data.put("account_type", selectedRadioButtonText)

                var rs: Long = db.insert("user", null, data)
                if (!rs.equals(-1)) {

                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Compte ajouté avec succès")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed1.text.clear()
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()

                } else {
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Record not addedd")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed1.text.clear()
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()
                }
            } else {
                Toast.makeText(this, "Veuillez Remplir les champs *", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginLink.setOnClickListener {
            val intent = Intent(this, login_form::class.java)
            startActivity(intent)
        }
    }
}