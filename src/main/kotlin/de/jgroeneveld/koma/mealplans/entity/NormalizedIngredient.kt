package de.jgroeneveld.koma.mealplans.entity

import de.jgroeneveld.koma.values.Ingredient
import de.jgroeneveld.koma.values.Quantity
import de.jgroeneveld.koma.values.QuantityUnit

enum class NormalizedQuantityUnit {
    G,
    Ml,
    Pieces,
    Unknown
}

data class NormalizedQuantity(
        val amount: Float,
        val unit: NormalizedQuantityUnit
) {
    fun isCompatibleWith(other: NormalizedQuantity) = this.unit == other.unit
}

data class NormalizedIngredient(
        val name: String,
        val quantity: NormalizedQuantity
) {
    fun combine(other: NormalizedIngredient): NormalizedIngredient {
        if (!this.isCompatibleWith(other)) {
            throw IllegalArgumentException("Ingredients not compatible")
        }

        return copy(
                quantity = this.quantity.copy(
                        amount = this.quantity.amount + other.quantity.amount
                )
        )
    }

    // isCompatibleWith means that normalizedIngredients can be added up
    fun isCompatibleWith(other: NormalizedIngredient): Boolean {
        return this.name == other.name && this.quantity.isCompatibleWith(other.quantity)
    }
}

fun normalizeIngredient(ingredient: Ingredient) = NormalizedIngredient(
        ingredient.name,
        normalizeQuantity(ingredient.quantity)
)

fun denormalizeIngredient(ingredient: NormalizedIngredient) = Ingredient(
        ingredient.name,
        denormalizeQuantity(ingredient.quantity)
)

fun normalizeQuantity(quantity: Quantity): NormalizedQuantity {
    val unit = when (quantity.unit) {
        QuantityUnit.G -> NormalizedQuantityUnit.G
        QuantityUnit.Kg -> NormalizedQuantityUnit.G
        QuantityUnit.L -> NormalizedQuantityUnit.Ml
        QuantityUnit.Ml -> NormalizedQuantityUnit.Ml
        QuantityUnit.Tsp -> NormalizedQuantityUnit.Ml
        QuantityUnit.Tbsp -> NormalizedQuantityUnit.Ml
        QuantityUnit.Pieces -> NormalizedQuantityUnit.Pieces
        QuantityUnit.Unknown -> NormalizedQuantityUnit.Unknown
    }

    val amount = when (quantity.unit) {
        QuantityUnit.G -> quantity.amount
        QuantityUnit.Kg -> quantity.amount * 1000F // TODO: remove magic numbers
        QuantityUnit.L -> quantity.amount * 1000F
        QuantityUnit.Ml -> quantity.amount
        QuantityUnit.Tsp -> quantity.amount * 5F
        QuantityUnit.Tbsp -> quantity.amount * 15F
        QuantityUnit.Pieces -> quantity.amount
        QuantityUnit.Unknown -> quantity.amount
    }

    return NormalizedQuantity(amount, unit)
}

fun denormalizeQuantity(quantity: NormalizedQuantity): Quantity {
    val unit = when (quantity.unit) {
        NormalizedQuantityUnit.G -> QuantityUnit.G
        NormalizedQuantityUnit.Ml -> QuantityUnit.Ml
        NormalizedQuantityUnit.Pieces -> QuantityUnit.Pieces
        NormalizedQuantityUnit.Unknown -> QuantityUnit.Unknown
    }

    return Quantity(quantity.amount, unit)
}