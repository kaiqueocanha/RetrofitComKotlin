package com.ocanha.retrofitcomkotlin.repositories

import com.ocanha.retrofitcomkotlin.model.Recipe
import com.ocanha.retrofitcomkotlin.rest.RetrofitService

class RecipeRepository constructor(private val retrofitService: RetrofitService) {

    fun saveRecipe(recipe: Recipe) = retrofitService.saveRecipe(recipe)

    fun getAllRecipes() = retrofitService.getAllRecipes()

}