package de.jgroeneveld.koma.parsing.ingredientparsers

import de.jgroeneveld.koma.values.Ingredient
import de.jgroeneveld.koma.values.Quantity
import de.jgroeneveld.koma.values.QuantityUnit

class FallbackParser(): LineParser() {
    override fun matches(line: String) = true

    override fun parse(line: String) = Ingredient(name = line, quantity = Quantity(0F, QuantityUnit.Unknown))

}