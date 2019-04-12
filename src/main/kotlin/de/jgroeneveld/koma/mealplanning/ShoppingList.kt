package de.jgroeneveld.koma.mealplanning

import de.jgroeneveld.koma.values.Ingredient

data class ShoppingList(
        val ingredients: Collection<Ingredient>
) {
    class Builder {
        val normalizedIngredients: MutableCollection<NormalizedIngredient> = mutableListOf()

        fun add(ingredient: Ingredient): Builder {
            val normalizedIngredient = normalizeIngredient(ingredient)

            val existingIngredient = findIngredient(normalizedIngredient)

            if (existingIngredient != null) {
                normalizedIngredients.remove(existingIngredient)
                normalizedIngredients.add(normalizedIngredient.combine(existingIngredient))
            } else {
                normalizedIngredients.add(normalizedIngredient)
            }

            return this
        }

        private fun findIngredient(ingredient: NormalizedIngredient): NormalizedIngredient? {
            return normalizedIngredients.find { it.isCompatibleWith(ingredient) }
        }

        fun build(): ShoppingList {
            val list = normalizedIngredients.map { denormalizeIngredient(it) }
            return ShoppingList(list)
        }
    }
}

