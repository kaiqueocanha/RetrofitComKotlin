package com.ocanha.retrofitcomkotlin.viewmodel.newrecipe

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocanha.retrofitcomkotlin.model.Recipe
import com.ocanha.retrofitcomkotlin.repositories.RecipeRepository

class NewRecipeViewModel constructor(private val repository: RecipeRepository) : ViewModel() {

    val status = MutableLiveData<Boolean>()

    fun saveRecipe(recipe: Recipe) {

        this.repository.saveRecipe(recipe)

        Handler().postDelayed({
            status.postValue(true)
        }, 2000)

    }


}