package com.ocanha.retrofitcomkotlin.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ocanha.retrofitcomkotlin.databinding.ActivityNewRecipeBinding
import com.ocanha.retrofitcomkotlin.model.Recipe
import com.ocanha.retrofitcomkotlin.repositories.RecipeRepository
import com.ocanha.retrofitcomkotlin.rest.RetrofitService
import com.ocanha.retrofitcomkotlin.viewmodel.main.MainViewModel
import com.ocanha.retrofitcomkotlin.viewmodel.main.MainViewModelFactory
import com.ocanha.retrofitcomkotlin.viewmodel.newrecipe.NewRecipeViewModel
import com.ocanha.retrofitcomkotlin.viewmodel.newrecipe.NewRecipeViewModelFactory

class NewRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewRecipeBinding
    private lateinit var viewModel: NewRecipeViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            NewRecipeViewModelFactory(RecipeRepository(retrofitService))
        ).get(
            NewRecipeViewModel::class.java
        )

        setupUi()

    }

    override fun onStart() {
        super.onStart()

        viewModel.status.observe(this, Observer {
            if (it) {
                Toast.makeText(
                    this,
                    "Receita salva com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Erro ao salvar a receita. Tente novamente.",
                    Toast.LENGTH_SHORT
                ).show()
                this.binding.fabNewRecipe.visibility = View.GONE
                this.binding.loadingView.dismiss()
            }
        })

    }

    private fun setupUi() {

        this.binding.apply {

            fabNewRecipe.setOnClickListener {

                if (edtRecipeTitle.text.isEmpty()) {
                    edtRecipeTitle.error = "Preencha o nome da receita"
                    edtRecipeTitle.requestFocus()
                    return@setOnClickListener
                }

                if (edtRecipeAuthor.text.isEmpty()) {
                    edtRecipeAuthor.error = "Preencha o autor da receita"
                    edtRecipeAuthor.requestFocus()
                    return@setOnClickListener
                }

                if (edtRecipeIngredients.text.isEmpty()) {
                    edtRecipeIngredients.error = "Preencha os ingredientes da receita"
                    edtRecipeIngredients.requestFocus()
                    return@setOnClickListener
                }

                if (!rbTypeSalt.isChecked && !rbTypeSweet.isChecked) {
                    Toast.makeText(
                        this@NewRecipeActivity,
                        "Selecione o tipo da receita",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                val type = if (rbTypeSweet.isChecked) {
                    "Doce"
                } else {
                    "Salgado"
                }

                val recipe = Recipe(
                    author = edtRecipeAuthor.text.toString(),
                    name = edtRecipeTitle.text.toString(),
                    type = type,
                    ingredients = edtRecipeIngredients.text.toString()
                )

                loadingView.show()
                fabNewRecipe.visibility = View.GONE
                viewModel.saveRecipe(recipe)

            }

        }

    }

}