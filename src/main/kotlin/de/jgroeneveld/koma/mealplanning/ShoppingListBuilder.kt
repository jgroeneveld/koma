package de.jgroeneveld.koma.mealplanning

import de.jgroeneveld.koma.recipes.entity.Ingredient

class ShoppingListBuilder {
    val ingredients: MutableCollection<Ingredient> = mutableListOf<Ingredient>()

    fun add(ingredient: Ingredient): ShoppingListBuilder {
        return this
    }

    fun build(): ShoppingList = ShoppingList(ingredients)
}