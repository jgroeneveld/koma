package de.jgroeneveld.koma.mealplans.entity

import de.jgroeneveld.koma.values.Ingredient

data class ShoppingList(
        val ingredients: Collection<Ingredient>
) {
    class Builder {
        val normalizedIngredients: MutableCollection<NormalizedIngredient> = mutableListOf()

        fun add(ingredient: Ingredient): Builder {
            val normalizedIngredient = normalizeIngredient(ingredient)

            val compatibleIngredient = findCompatible(normalizedIngredient)

            if (compatibleIngredient != null) {
                normalizedIngredients.remove(compatibleIngredient)
                normalizedIngredients.add(normalizedIngredient.combine(compatibleIngredient))
            } else {
                normalizedIngredients.add(normalizedIngredient)
            }

            return this
        }

        private fun findCompatible(ingredient: NormalizedIngredient): NormalizedIngredient? {
            return normalizedIngredients.find { it.isCompatibleWith(ingredient) }
        }

        fun build(): ShoppingList {
            val list = normalizedIngredients.map { denormalizeIngredient(it) }
            return ShoppingList(list)
        }
    }
}

