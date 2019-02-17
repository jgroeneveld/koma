package de.jgroeneveld.koma.parsing

import de.jgroeneveld.koma.recipes.entity.Ingredient

data class ParsedRecipe(
        var id: String? = null,

        var title: String = "",
        var descriptionMk: String = "",
        var ingredientsMk: String = "",
        var stepsMk: String = "",
        var text: String = "",

        var tags: Iterable<String> = listOf(),
        var ingredients: Iterable<Ingredient>? = listOf()
)