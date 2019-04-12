package de.jgroeneveld.koma.parsing

import de.jgroeneveld.koma.values.Ingredient

data class ParsedRecipe(
        val title: String = "",
        val description: String = "",
        val ingredients: String = "",
        val steps: String = "",

        val tags: Iterable<String> = listOf(),
        val ingredientsList: Iterable<Ingredient> = listOf()
)