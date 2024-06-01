package com.example.cardapioonline

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardapioonline.adapter.FoodAdapter
import com.example.cardapioonline.databinding.ActivityMainBinding
import com.example.cardapioonline.model.Food

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var foodAdapter: FoodAdapter
    private val foodList: MutableList<Food> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewFood = binding.RecyclerViewFood
        recyclerViewFood.layoutManager = LinearLayoutManager(this)
        recyclerViewFood.setHasFixedSize(true)
        foodAdapter = FoodAdapter(this, foodList)
        recyclerViewFood.adapter = foodAdapter

        binding.button.setOnClickListener {
            val selectedFoods = foodAdapter.getSelectedFoods()
            val foodNames = ArrayList<String>()
            val foodQuantities = ArrayList<Int>()
            val totalValues = ArrayList<Int>()

            for (food in selectedFoods) {
                foodNames.add(food.foodName)
                foodQuantities.add(food.quantidade)
                totalValues.add(food.quantidade * food.price)
            }

            val intent = Intent(this, FinalizarActivity::class.java).apply {
                putStringArrayListExtra("foodNames", foodNames)
                putIntegerArrayListExtra("foodQuantities", foodQuantities)
                putIntegerArrayListExtra("totalValues", totalValues)
            }
            startActivity(intent)
        }

        getFood()
    }

    private fun getFood() {
        val food1 = Food(
            imgFood = R.drawable.food1,
            foodName = "Camarão com fritas",
            foodDescription = "descricao aleatoria",
            price = 50,
            quantidade = 0
        )
        foodList.add(food1)

        val food2 = Food(
            imgFood = R.drawable.food2,
            foodName = "Sushi",
            foodDescription = "descricao aleatoria",
            price = 60,
            quantidade = 0
        )
        foodList.add(food2)

        val food3 = Food(
            imgFood = R.drawable.food3,
            foodName = "Torta salgada com rosbife",
            foodDescription = "descricao aleatoria",
            price = 70,
            quantidade = 0
        )
        foodList.add(food3)

        val food4 = Food(
            imgFood = R.drawable.food4,
            foodName = "Prato feito",
            foodDescription = "descricao aleatoria",
            price = 70,
            quantidade = 0
        )
        foodList.add(food4)

        val food5 = Food(
            imgFood = R.drawable.food5,
            foodName = "Prato feito do chefe",
            foodDescription = "xobla",
            price = 80,
            quantidade = 0
        )
        foodList.add(food5)
    }
}
