package com.dam2024m8uf1_tfinal.gsonproba

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dam2024m8uf1_tfinal.gsonproba.Entitis.Persona

class MainActivity : AppCompatActivity() {
    private lateinit var nc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val persona = Persona("Joan", 25, "joan@example.com")
        PersistenciaDadesSingleton.getInstance().guardarPersona(persona, this)
        nc = findViewById(R.id.Nom)

        val personaDesada = PersistenciaDadesSingleton.getInstance().carregarPersona(this)
        if (personaDesada != null) {
            nc.text = personaDesada.nom
            println("Nom: ${personaDesada.nom}, Edat: ${personaDesada.edat}, Email: ${personaDesada.email}")
        } else {
            println("No s'ha pogut carregar la persona.")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
