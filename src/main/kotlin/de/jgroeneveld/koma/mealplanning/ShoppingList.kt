package de.jgroeneveld.koma.mealplanning

import de.jgroeneveld.koma.recipes.entity.Ingredient

data class ShoppingList(
        val ingredients: Collection<Ingredient>
)