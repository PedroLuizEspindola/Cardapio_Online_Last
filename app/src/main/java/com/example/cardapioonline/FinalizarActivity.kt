package com.example.cardapioonline

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardapioonline.databinding.ActivityFinalizarBinding

class FinalizarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinalizarBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalizarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        // Recebe os valores da Intent
        val foodNames = intent.getStringArrayListExtra("foodNames")
        val foodQuantities = intent.getIntegerArrayListExtra("foodQuantities")
        val totalValues = intent.getIntegerArrayListExtra("totalValues")

        // Calcula o valor total
        val totalSum = totalValues?.sum() ?: 0

        // Exibe o valor total no TextView
        binding.tvTotalSum.text = "Total: $$totalSum"

        // Exibe os detalhes dos produtos no TextView
        val details = StringBuilder()
        if (foodNames != null && foodQuantities != null) {
            for (i in foodNames.indices) {
                details.append("${foodNames[i]}: ${foodQuantities[i]}\n")
            }
        }
        binding.tvProductDetails.text = details.toString()

        // Configura o botão para enviar o pedido
        binding.buttonFinalize.setOnClickListener {
            if (totalSum > 0) {
                if (foodNames != null && foodQuantities != null) {
                    val foodNamesStr = foodNames.joinToString(",")
                    val foodQuantitiesStr = foodQuantities.joinToString(",")

                    // Salva os pedidos no banco de dados com o valor total
                    dbHelper.insertOrder(foodNamesStr, foodQuantitiesStr, totalSum.toDouble())
                }
                // Exibe um Toast com a mensagem de sucesso
                Toast.makeText(this, "Pedido feito com sucesso", Toast.LENGTH_SHORT).show()

                // Navega de volta para MainActivity
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()  // Encerra a FinalizarActivity
            } else {
                // Exibe um Toast informando que o pedido não pode ser feito
                Toast.makeText(this, "O pedido não pode ser feito porque nada foi adicionado ao carrinho", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
