package de.jgroeneveld.koma.mealplanning

import de.jgroeneveld.koma.values.Ingredient
import de.jgroeneveld.koma.recipes.entity.Recipe

data class Mealplan(
        val recipes: Collection<Recipe>
) {
    fun shoppingList(): Collection<Ingredient> {
        return listOf()
    }
}