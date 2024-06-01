package com.example.cardapioonline

import ComidaAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardapioonline.databinding.ActivityComidaBinding
import com.example.cardapioonline.model.Comida
import kotlin.random.Random

class ComidaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val comidas = listOf(
            Comida("Camarão com fritas", 50),
            Comida("Sushi", 60),
            Comida("Torta salgada", 70),
            Comida("Prato feito", 70),
            Comida("prato feito do chefe", 80)
        )

        val adapter = ComidaAdapter(comidas)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}
