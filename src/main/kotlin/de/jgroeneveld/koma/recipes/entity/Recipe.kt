package de.jgroeneveld.koma.recipes.entity

data class Recipe(
        val title: String,
        val description: String,
        val ingredients: String,
        val steps: String,

        val id: String,
        val ingredientsList: Iterable<Ingredient>,
        val tags: Iterable<String>
)