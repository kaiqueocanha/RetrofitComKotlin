package com.ocanha.retrofitcomkotlin.viewmodel.newrecipe

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocanha.retrofitcomkotlin.model.Recipe
import com.ocanha.retrofitcomkotlin.repositories.RecipeRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class NewRecipeViewModel constructor(private val repository: RecipeRepository) : ViewModel() {

    val status = MutableLiveData<Boolean>()

    fun saveRecipe(recipe: Recipe) {

        val request = repository.saveRecipe(recipe)
        request.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    status.postValue(true)
                } else {
                    status.postValue(false)
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                status.postValue(false)
            }
        })

    }


}