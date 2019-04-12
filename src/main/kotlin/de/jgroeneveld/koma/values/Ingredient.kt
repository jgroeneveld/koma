package de.jgroeneveld.koma.values

data class Ingredient(val name: String, val quantity: Quantity) {
    fun toReadable(): String {
        return when (quantity.unit) {
            QuantityUnit.Unknown -> name
            QuantityUnit.Pieces -> "${quantity.amount.toInt()} ${name}"
            else -> "${quantity.amount.toInt()}${quantity.unit} ${name}"
        }
    }
}