package de.jgroeneveld.koma.parsing

import de.jgroeneveld.koma.recipes.entity.Ingredient

data class ParsedRecipe(
        var title: String = "",
        var descriptionMd: String = "",
        var ingredientsMd: String = "",
        var stepsMd: String = "",
        var footerMd: String = "",

        var id: String? = null,
        var tags: Iterable<String> = listOf(),
        var ingredients: Iterable<Ingredient>? = listOf()
)