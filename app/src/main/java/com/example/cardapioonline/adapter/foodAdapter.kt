package com.example.cardapioonline.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardapioonline.databinding.FoodItemBinding
import com.example.cardapioonline.model.Food

class FoodAdapter(private val context: Context, private val foodList: MutableList<Food>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val listItem = FoodItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return FoodViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val comida = foodList[position]
        holder.imgFood.setBackgroundResource(comida.imgFood!!)
        holder.txtFoodName.text = comida.foodName
        holder.txtFoodDescription.text = comida.foodDescription
        holder.foodPrice.text = comida.price.toString()
        holder.txtQuantidade.text = comida.quantidade.toString()
        holder.txtValorText.text = "Total: ${(comida.quantidade * comida.price)}"

        holder.btnMais.setOnClickListener {
            comida.quantidade++
            holder.txtQuantidade.text = comida.quantidade.toString()
            holder.txtValorText.text = "Total: ${(comida.quantidade * comida.price)}"
        }
        holder.btnMenos.setOnClickListener {
            if (comida.quantidade > 0) {
                comida.quantidade--
                holder.txtQuantidade.text = comida.quantidade.toString()
                holder.txtValorText.text = "Total: ${(comida.quantidade * comida.price)}"
            }
        }
    }

    fun getSelectedFoods(): List<Food> {
        return foodList.filter { it.quantidade > 0 }
    }

    inner class FoodViewHolder(binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgFood = binding.imgfood
        val txtFoodName = binding.txtFoodName
        val txtFoodDescription = binding.txtFoodDescription
        val foodPrice = binding.txtPrice
        val txtQuantidade = binding.txtQuantidade
        val btnMenos = binding.btnMenos
        val btnMais = binding.btnMais
        val txtValorText = binding.txValorTotal
    }
}
