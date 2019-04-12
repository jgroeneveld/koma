package de.jgroeneveld.koma.recipes.entity

import de.jgroeneveld.koma.values.Ingredient

data class Recipe(
        val title: String,
        val description: String,
        val ingredients: String,
        val steps: String,

        val id: String,
        val ingredientsList: Iterable<Ingredient>,
        val tags: Iterable<String>
)