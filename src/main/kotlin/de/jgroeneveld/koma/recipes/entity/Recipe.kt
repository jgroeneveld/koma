package de.jgroeneveld.koma.recipes.entity

data class Recipe(
        val title: String,
        val steps: Iterable<String>,
        val text: String,
        val tag: Iterable<String>
)