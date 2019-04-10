package de.jgroeneveld.koma.values

enum class QuantityUnit {
    Kg,
    G,
    L,
    Ml,
    Tsp,
    Tbsp,
    Pieces,
    Unknown;
}

data class Quantity(
        val amount: Float,
        val unit: QuantityUnit
)

