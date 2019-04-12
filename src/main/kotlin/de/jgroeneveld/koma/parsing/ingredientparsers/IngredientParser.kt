package de.jgroeneveld.koma.parsing.ingredientparsers

import de.jgroeneveld.koma.values.Ingredient
import de.jgroeneveld.koma.values.QuantityUnit

class IngredientParser {
    fun parse(line: String): Ingredient {
        // TODO: i18n
        val parsers = listOf(
                UnitLineParser("ml", QuantityUnit.Ml),
                UnitLineParser("l", QuantityUnit.L),
                UnitLineParser("g", QuantityUnit.G),
                UnitLineParser("kg", QuantityUnit.Kg),
                UnitLineParser("el", QuantityUnit.Tbsp),
                UnitLineParser("tl", QuantityUnit.Tsp),
                UnitLineParser("", QuantityUnit.Pieces),
                FallbackParser()
        )

        for (parser in parsers) {
            if (parser.matches(line)) {
                return parser.parse(line)
            }
        }

        throw Exception("No parser found for '$line'")
    }
}
