package com.ocanha.retrofitcomkotlin.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocanha.retrofitcomkotlin.adapters.RecipesAdapter
import com.ocanha.retrofitcomkotlin.databinding.ActivityMainBinding
import com.ocanha.retrofitcomkotlin.model.Recipe
import com.ocanha.retrofitcomkotlin.repositories.RecipeRepository
import com.ocanha.retrofitcomkotlin.rest.RetrofitService
import com.ocanha.retrofitcomkotlin.viewmodel.main.MainViewModel
import com.ocanha.retrofitcomkotlin.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()

    private val adapter = RecipesAdapter { recipe ->
        openRecipe(recipe)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(RecipeRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        setupUi()
    }

    private fun setupUi() {

        binding.fabNewRecipe.setOnClickListener {
            startActivity(Intent(this, NewRecipeActivity::class.java))
        }

        binding.rvRecipes.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.rvRecipes.layoutManager = LinearLayoutManager(this)
        binding.rvRecipes.adapter = adapter


    }

    override fun onResume() {
        super.onResume()

        this.binding.loadingView.show()
        viewModel.getAllRecipes()

    }

    override fun onStart() {
        super.onStart()

        viewModel.recipesList.observe(this, Observer { recipes ->
            this.binding.loadingView.dismiss()
            updateRecipesList(recipes)
        })

        viewModel.errorMessage.observe(this, Observer {
            this.binding.loadingView.dismiss()
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            updateRecipesList(listOf())
        })

    }

    private fun updateRecipesList(recipes: List<Recipe>) {

        adapter.setRecipesList(recipes)

        binding.apply {
            if (recipes.isEmpty()) {
                rvRecipes.visibility = View.GONE
                imgNoRecipes.visibility = View.VISIBLE
                imgNoRecipes2.visibility = View.VISIBLE
                tvNoRecipes.visibility = View.VISIBLE
            } else {
                rvRecipes.visibility = View.VISIBLE
                imgNoRecipes.visibility = View.GONE
                imgNoRecipes2.visibility = View.GONE
                tvNoRecipes.visibility = View.GONE
            }
        }

    }

    private fun openRecipe(recipe: Recipe) {

    }
}