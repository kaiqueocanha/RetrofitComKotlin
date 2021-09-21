package com.ocanha.retrofitcomkotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ocanha.retrofitcomkotlin.databinding.ResItemRecipesBinding
import com.ocanha.retrofitcomkotlin.model.Recipe

class RecipesAdapter(private val onItemClicked: (Recipe) -> Unit) :
    RecyclerView.Adapter<MainViewHolder>() {

    private var recipes = mutableListOf<Recipe>()

    fun setRecipesList(lives: List<Recipe>) {

        this.recipes = lives.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemRecipesBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val live = recipes[position]
        holder.bind(live, onItemClicked)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}

class MainViewHolder(val binding: ResItemRecipesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recipe: Recipe, onItemClicked: (Recipe) -> Unit) {

        binding.tvRecipeTitle.text = recipe.name
        binding.tvRecipeAuthor.text = "Autor: ${recipe.author}"
        binding.tvRecipeType.text = "Tipo: ${recipe.type}"

        itemView.setOnClickListener {
            onItemClicked(recipe)
        }

    }

}