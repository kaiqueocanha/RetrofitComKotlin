package com.ocanha.retrofitcomkotlin.model

data class Recipe(
    val author: String,
    val id: String? = null,
    val ingredients: String,
    val name: String,
    val type: String
)