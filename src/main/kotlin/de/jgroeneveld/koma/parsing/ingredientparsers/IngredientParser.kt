package de.jgroeneveld.koma.parsing.ingredientparsers

import de.jgroeneveld.koma.recipes.entity.Ingredient
import de.jgroeneveld.koma.recipes.entity.Quantity

class IngredientParser {
    fun parse(line: String): Ingredient {
        val parsers = listOf(
                UnitLineParser("ml", Quantity.Ml),
                UnitLineParser("l", Quantity.L),
                UnitLineParser("g", Quantity.G),
                UnitLineParser("kg", Quantity.Kg),
                UnitLineParser("el", Quantity.Tbsp), // TODO: i18n
                UnitLineParser("tl", Quantity.Tsp), // TODO: i18n
                UnitLineParser("", Quantity.Pieces),
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
