package de.jgroeneveld.koma.recipes.entity

data class Recipe(
        val id: String,
        val ingredients: Iterable<Ingredient>,
        val steps: Iterable<String>,
        val text: String,
        val tags: Iterable<String>,
        val title: String
)