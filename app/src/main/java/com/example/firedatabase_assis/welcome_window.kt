package com.example.firedatabase_assis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.firedatabase_assis.databinding.ActivityWelcomeWindowBinding

class welcome_window : AppCompatActivity() {
    private lateinit var bind: ActivityWelcomeWindowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityWelcomeWindowBinding.inflate(layoutInflater)
        setContentView(bind.root)
        var value=intent.getStringExtra("email")+" : "+intent.getStringExtra("accountType")
        bind.uname.text=value

        bind.logout.setOnClickListener {
            startActivity(Intent(this,login_form::class.java))
        }

        bind.btnFillForm.setOnClickListener {
            val intent= Intent(this,SendForm::class.java)
            startActivity(intent)
        }
    }


}