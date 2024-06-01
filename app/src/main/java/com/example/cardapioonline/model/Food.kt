package com.example.cardapioonline.model

import android.widget.Button

class Food(
    val imgFood:Int? = null,
    val foodName:String,
    val foodDescription:String? = null,
    val price:Int,
    val btnMenos: Button? = null,
    val btnMais: Button? = null,
    var txtViewValor:String? = null,
    var quantidade: Int = 0
)

