package com.ocanha.retrofitcomkotlin.repositories

import android.util.Log
import com.ocanha.retrofitcomkotlin.model.Recipe

class RecipeRepository {

    fun saveRecipe(recipe: Recipe) {

        //Salvar receita
        Log.i("Kaique", recipe.toString())

    }

    fun getAllRecipes(): List<Recipe> {

        val recipes = mutableListOf<Recipe>()

        for (i in 1..10) {
            recipes.add(
                Recipe(
                    "Kaique Ocanha",
                    i.toString(),
                    "2 xícaras de trigo. 3 colheres de açucar. 2 colheres de manteiga.",
                    "Bolo de Cenoura",
                    "Doce"
                )
            )
        }

        return recipes

    }

}