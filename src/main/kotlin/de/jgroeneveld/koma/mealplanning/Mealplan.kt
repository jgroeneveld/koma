package de.jgroeneveld.koma.mealplanning

import de.jgroeneveld.koma.values.Ingredient
import de.jgroeneveld.koma.recipes.entity.Recipe

data class Mealplan(
        val recipes: Collection<Recipe>
) {
    fun shoppingList(): ShoppingList {
        val builder = ShoppingList.Builder()

        for(recipe in recipes) {
            for(ingredient in recipe.ingredientsList) {
                builder.add(ingredient)
            }
        }

        return builder.build()
    }
}