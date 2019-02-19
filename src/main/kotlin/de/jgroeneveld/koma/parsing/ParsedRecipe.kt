package de.jgroeneveld.koma.parsing

import de.jgroeneveld.koma.recipes.entity.Ingredient

data class ParsedRecipe(
        val title: String = "",
        val descriptionMd: String = "",
        val ingredientsMd: String = "",
        val stepsMd: String = "",
        val footerMd: String = "",

        val id: String? = null,
        val tags: Iterable<String> = listOf(),
        val ingredients: Iterable<Ingredient>? = listOf()
)